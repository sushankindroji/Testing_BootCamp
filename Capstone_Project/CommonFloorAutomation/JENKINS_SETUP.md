# Jenkins Setup for JMeter Performance Testing
## CommonFloor Capstone Project - CI/CD Pipeline

---

## 📋 Table of Contents
1. [Prerequisites](#prerequisites)
2. [Jenkins Installation](#jenkins-installation)
3. [Plugin Installation](#plugin-installation)
4. [Job Configuration](#job-configuration)
5. [Running Tests](#running-tests)
6. [Viewing Reports](#viewing-reports)
7. [Troubleshooting](#troubleshooting)

---

## ✅ Prerequisites

### System Requirements
- Jenkins 2.361 or later
- Java 11 or later
- Apache JMeter 5.5 or later
- Git client
- Maven 3.8.1 or later (optional)

### Access Requirements
- Administrator access to Jenkins
- GitHub repository access (read)
- Git credentials configured in Jenkins

### Installed Software
```bash
# Verify installations
java -version
jmeter --version
git --version
```

---

## 📦 Jenkins Installation

### Option 1: Docker (Recommended)
```bash
docker run -d -p 8080:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  --name jenkins \
  jenkins/jenkins:latest
```

### Option 2: macOS (Homebrew)
```bash
brew install jenkins-lts
brew services start jenkins-lts

# Jenkins will run on http://localhost:8080
```

### Option 3: Linux (Debian/Ubuntu)
```bash
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt-get update
sudo apt-get install jenkins -y
sudo systemctl start jenkins
```

### Option 4: Linux (CentOS/RHEL)
```bash
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum install jenkins -y
sudo systemctl start jenkins
```

### Access Jenkins
```
http://localhost:8080
```

**First Time Setup:**
1. Retrieve initial admin password:
   ```bash
   # Docker
   docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
   
   # macOS/Linux
   cat /var/lib/jenkins/secrets/initialAdminPassword
   ```
2. Enter the password in Jenkins UI
3. Install recommended plugins
4. Create first admin user

---

## 🔌 Plugin Installation

### Required Plugins for JMeter

1. **Performance Plugin** (for JMeter result parsing)
2. **Git Plugin** (for GitHub integration)
3. **Pipeline Plugin** (for Jenkinsfile support)
4. **Log Parser Plugin** (optional, for better log visibility)

### Installation Steps

1. Go to: `Manage Jenkins` → `Manage Plugins`
2. Click `Available` tab
3. Search for and install:
   - `Performance Plugin`
   - `Pipeline`
   - `Git`
   - `Log Parser Plugin` (optional)
4. Restart Jenkins

### Alternative: Via CLI
```bash
# If Jenkins is running in Docker
docker exec jenkins jenkins-plugin-cli.sh \
  --plugins performance:3.20 pipeline:2.6 git:5.0.2
```

---

## ⚙️ Job Configuration

### Method 1: Create Pipeline Job (Recommended)

**Step 1: Create New Job**
1. Click `New Item`
2. Enter job name: `CommonFloor-JMeter-Performance-Test`
3. Select `Pipeline`
4. Click `OK`

**Step 2: Configure Pipeline**

In the Pipeline section:

```
Definition: Pipeline script from SCM
SCM: Git
Repository URL: https://github.com/sushankindroji/Testing_BootCamp.git
Branch: */main
Script Path: Capstone_Project/CommonFloorAutomation/Jenkinsfile
```

**Step 3: Configure Git Credentials** (if private repo)
1. Click `Add` → `Jenkins`
2. Set credentials (SSH key or username/password)
3. Select the credential

**Step 4: Advanced Options**
1. Check: `Lightweight checkout`
2. Set `Poll SCM` trigger: `H/6 * * * *` (every 6 hours)

**Step 5: Save**

### Method 2: Manual Job Configuration

**Step 1: Create Freestyle Job**
1. Click `New Item`
2. Enter name: `CommonFloor-JMeter-Test`
3. Select `Freestyle job`
4. Click `OK`

**Step 2: Source Code Management**
- Select `Git`
- Repository URL: `https://github.com/sushankindroji/Testing_BootCamp.git`
- Branch: `*/main`

**Step 3: Build Triggers**
- Check `GitHub hook trigger for GITScm polling`
- Or set `Poll SCM`: `H H * * *` (daily at midnight)

**Step 4: Build Steps**
Click `Add build step` → `Execute shell`

```bash
#!/bin/bash
set -e

echo "========== JMETER PERFORMANCE TEST =========="
echo "Test Plan: jmeter/HTTP Request.jmx"
echo "Threads: 50"
echo "Ramp-up: 1s"

cd Capstone_Project/CommonFloorAutomation

# Create results directory
mkdir -p test-results

# Run JMeter test
jmeter -n \
    -t jmeter/HTTP\ Request.jmx \
    -l test-results/results-${BUILD_NUMBER}.jtl \
    -e -o test-results/html-report-${BUILD_NUMBER} \
    -Jthreads=50 \
    -Jrampup=1 \
    -Jloops=1 \
    -j test-results/jmeter-${BUILD_NUMBER}.log

echo "✅ Test execution completed"
```

**Step 5: Post-build Actions**
1. Click `Add post-build action` → `Publish Performance test result report`
2. Set:
   - Results file pattern: `**/results-*.jtl`
   - Save build label to property file: (optional)

**Step 6: Archive Artifacts**
1. Click `Add post-build action` → `Archive the artifacts`
2. Set: `Capstone_Project/CommonFloorAutomation/jmeter-results-*/**`

**Step 7: Save**

---

## 🚀 Running Tests

### Manual Trigger
1. Go to job page: `CommonFloor-JMeter-Performance-Test`
2. Click `Build Now`
3. Select parameters (optional):
   - Thread Count: 50
   - Ramp-up Time: 1
   - Loop Count: 1
4. Monitor build progress in `Build History`

### Automatic Trigger (Webhook)

**GitHub Webhook Setup:**
1. Go to GitHub repository settings
2. Click `Webhooks` → `Add webhook`
3. Payload URL: `http://your-jenkins-url/github-webhook/`
4. Content type: `application/json`
5. Events: `Just the push event`
6. Click `Add webhook`

### Scheduled Trigger

In Jenkins job:
1. Go to `Configure`
2. Under `Build Triggers`
3. Check `Build periodically`
4. Set schedule:
   ```
   H H * * *    # Daily at midnight
   H */6 * * *  # Every 6 hours
   H 0 * * 1-5  # Weekdays at midnight
   ```

---

## 📊 Viewing Reports

### Jenkins Console Output
1. Click on build number in `Build History`
2. Click `Console Output`
3. Scroll to see test execution logs

### HTML Test Report
1. In build page, look for `Artifacts` section
2. Download or view `html-report-{BUILD_NUMBER}/` folder
3. Open `index.html` in browser

### Performance Trend Graph
1. From job page, click `Performance Report`
2. View graphs of:
   - Response time trends
   - Throughput over time
   - Error rate trends
   - Load distribution

### Download Results
1. Go to build page
2. Click `Artifacts`
3. Download:
   - `jmeter-results-{BUILD_NUMBER}/results-{BUILD_NUMBER}.jtl`
   - `jmeter-results-{BUILD_NUMBER}/html-report-{BUILD_NUMBER}.tar.gz`

---

## 🔧 JMeter Test Plan Parameters

### Available Parameters in Jenkinsfile

| Parameter | Default | Description |
|-----------|---------|-------------|
| `TEST_PLAN` | `jmeter/HTTP Request.jmx` | Test plan file path |
| `THREAD_COUNT` | 50 | Number of threads |
| `RAMP_UP_TIME` | 1 | Ramp-up time in seconds |
| `LOOP_COUNT` | 1 | Loops per thread |

### Custom Test Plans

To use different test plan:
```bash
# When triggering build, set:
TEST_PLAN=jmeter/JMeterCaseStudy.jmx
THREAD_COUNT=100
RAMP_UP_TIME=5
LOOP_COUNT=2
```

---

## 📈 Performance Metrics Captured

Jenkins captures from JMeter results:

- **Response Time:**
  - Min, Max, Average
  - 90th percentile, 95th percentile, 99th percentile

- **Throughput:**
  - Requests per second
  - Total throughput

- **Error Rate:**
  - Number of errors
  - Error percentage

- **Load:**
  - Active threads
  - Thread distribution

- **Test Execution:**
  - Total samples
  - Pass/Fail count
  - Test duration

---

## 🐛 Troubleshooting

### Problem: JMeter command not found
**Solution:**
```bash
# Update Jenkinsfile with full path to JMeter
JMETER_HOME=/usr/local/bin/jmeter
$JMETER_HOME/bin/jmeter -n ...

# Or add JMeter to PATH in Jenkins
# Manage Jenkins → Configure System → Environment variables
# Add: PATH=/usr/local/bin/jmeter/bin:$PATH
```

### Problem: Permission denied for JMeter
**Solution:**
```bash
# Make JMeter executable
chmod +x /usr/local/bin/jmeter/bin/jmeter

# Or run as different user
sudo usermod -aG jenkins $USER
```

### Problem: Git repository not accessible
**Solution:**
1. In Jenkins: `Manage Jenkins` → `Manage Credentials`
2. Add SSH key or username/password
3. In job config, select credential

### Problem: Performance Plugin not parsing results
**Solution:**
1. Ensure results file is in JTL format
2. Verify file path in post-build action
3. Check plugin version compatibility
4. Restart Jenkins

### Problem: Out of memory during test
**Solution:**
```bash
# Update Jenkins JVM heap size
# In Jenkins startup:
export JENKINS_JAVA_OPTIONS="-Xmx2g -Xms1g"

# For Docker:
docker run -e JAVA_OPTS="-Xmx2g" ...
```

### Problem: Test results not archived
**Solution:**
1. Verify artifact path is correct
2. Check build permissions
3. Ensure results directory exists
4. Check Jenkins workspace permissions

---

## 📞 Support & Documentation

- **Jenkins Official Docs:** https://www.jenkins.io/doc/
- **JMeter Official Docs:** https://jmeter.apache.org/
- **Performance Plugin:** https://plugins.jenkins.io/performance/
- **GitHub Integration:** https://plugins.jenkins.io/github/

---

## ✅ Verification Checklist

After setup, verify:

- [ ] Jenkins running and accessible
- [ ] All required plugins installed
- [ ] GitHub repository accessible
- [ ] JMeter installed and accessible
- [ ] Job created and configured
- [ ] Test plan files present in repository
- [ ] Build can be triggered manually
- [ ] Test executes successfully
- [ ] HTML report is generated
- [ ] Results are archived

---

## 📝 Example Jenkins Output

```
========== CHECKOUT CODE ==========
✅ Code checked out from GitHub
git version 2.37.1

========== SETUP ENVIRONMENT ==========
JMeter Home: /usr/local/bin/jmeter
Results Directory: /var/jenkins_home/workspace/job/jmeter-results-123
Test Plan: jmeter/HTTP Request.jmx
Threads: 50
Ramp-up: 1 seconds
Loops: 1

========== RUN JMETER PERFORMANCE TEST ==========
Starting JMeter test execution...
Test Plan: jmeter/HTTP Request.jmx
✅ JMeter test execution completed successfully

========== GENERATE TEST REPORTS ==========
✅ JTL results file created successfully
Results file size: 2.5M
✅ HTML report directory created successfully

========== ANALYZE TEST RESULTS ==========
========== TEST METRICS ==========
Total Samples: 50
Successful: 50
Failed: 0
✅ All tests PASSED - 0 errors!

========== ARCHIVE TEST REPORTS ==========
✅ HTML report archived: html-report-123.tar.gz

✅ JMeter Performance Test Pipeline PASSED
```

---

**Last Updated:** September 17, 2026  
**Author:** Sushank Indroji  
**Project:** CommonFloor Capstone - QA Automation