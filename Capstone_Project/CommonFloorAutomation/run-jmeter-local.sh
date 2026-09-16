#!/bin/bash

# Local JMeter Test Runner
# CommonFloor Capstone Project - Performance Testing Script
# This script helps run JMeter tests locally before pushing to Jenkins

set -e

# Color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
TEST_PLAN="${1:-jmeter/HTTP Request.jmx}"
THREAD_COUNT="${2:-50}"
RAMP_UP_TIME="${3:-1}"
LOOP_COUNT="${4:-1}"
RESULTS_DIR="local-jmeter-results"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
JTL_FILE="${RESULTS_DIR}/results_${TIMESTAMP}.jtl"
HTML_REPORT_DIR="${RESULTS_DIR}/html-report_${TIMESTAMP}"
LOG_FILE="${RESULTS_DIR}/jmeter_${TIMESTAMP}.log"

# Functions
print_header() {
    echo -e "${BLUE}========== $1 ==========${NC}"
}

print_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

print_error() {
    echo -e "${RED}❌ $1${NC}"
}

print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

print_info() {
    echo -e "${BLUE}ℹ️  $1${NC}"
}

# Check if JMeter is installed
check_jmeter() {
    print_header "CHECKING JMETER INSTALLATION"
    
    if command -v jmeter &> /dev/null; then
        print_success "JMeter is installed"
        JMETER_VERSION=$(jmeter --version | head -1)
        print_info "Version: $JMETER_VERSION"
    else
        print_error "JMeter is not installed or not in PATH"
        echo ""
        echo "Installation instructions:"
        echo "1. macOS: brew install jmeter"
        echo "2. Linux: apt-get install jmeter (or yum install jmeter)"
        echo "3. Windows: Download from https://jmeter.apache.org/"
        echo ""
        exit 1
    fi
}

# Check if test plan exists
check_test_plan() {
    print_header "VERIFYING TEST PLAN"
    
    if [ -f "$TEST_PLAN" ]; then
        print_success "Test plan found: $TEST_PLAN"
        FILE_SIZE=$(du -h "$TEST_PLAN" | cut -f1)
        print_info "File size: $FILE_SIZE"
    else
        print_error "Test plan not found: $TEST_PLAN"
        echo ""
        echo "Available test plans:"
        ls -1 jmeter/*.jmx 2>/dev/null || echo "No .jmx files found in jmeter/ directory"
        exit 1
    fi
}

# Setup results directory
setup_results_dir() {
    print_header "SETUP"
    
    mkdir -p "$RESULTS_DIR"
    print_success "Results directory created: $RESULTS_DIR"
    
    print_info "Test Configuration:"
    echo "  Test Plan: $TEST_PLAN"
    echo "  Threads: $THREAD_COUNT"
    echo "  Ramp-up Time: ${RAMP_UP_TIME}s"
    echo "  Loop Count: $LOOP_COUNT"
    echo "  Results JTL: $JTL_FILE"
    echo "  HTML Report: $HTML_REPORT_DIR"
}

# Run JMeter test
run_test() {
    print_header "RUNNING JMETER TEST"
    
    echo "Starting test execution..."
    echo ""
    
    jmeter -n \
        -t "$TEST_PLAN" \
        -l "$JTL_FILE" \
        -e -o "$HTML_REPORT_DIR" \
        -Jthreads="$THREAD_COUNT" \
        -Jrampup="$RAMP_UP_TIME" \
        -Jloops="$LOOP_COUNT" \
        -j "$LOG_FILE"
    
    TEST_EXIT_CODE=$?
    
    if [ $TEST_EXIT_CODE -eq 0 ]; then
        print_success "JMeter test execution completed successfully"
    else
        print_error "JMeter test execution failed with exit code: $TEST_EXIT_CODE"
        exit $TEST_EXIT_CODE
    fi
}

# Analyze results
analyze_results() {
    print_header "ANALYZING RESULTS"
    
    if [ ! -f "$JTL_FILE" ]; then
        print_error "Results file not found: $JTL_FILE"
        exit 1
    fi
    
    print_success "Results file found"
    FILE_SIZE=$(du -h "$JTL_FILE" | cut -f1)
    print_info "Results file size: $FILE_SIZE"
    
    # Count samples
    TOTAL_SAMPLES=$(grep -c "label=" "$JTL_FILE" 2>/dev/null || echo "0")
    SUCCESS_COUNT=$(grep 'success="true"' "$JTL_FILE" 2>/dev/null | wc -l)
    ERROR_COUNT=$(grep 'success="false"' "$JTL_FILE" 2>/dev/null | wc -l)
    
    echo ""
    echo "========== TEST METRICS =========="
    echo "Total Samples: $TOTAL_SAMPLES"
    echo "Successful: $SUCCESS_COUNT"
    echo "Failed: $ERROR_COUNT"
    
    if [ "$ERROR_COUNT" -eq 0 ]; then
        print_success "All tests PASSED - 0 errors!"
    else
        print_warning "$ERROR_COUNT test(s) failed"
    fi
}

# Check HTML report
check_report() {
    print_header "REPORT GENERATION"
    
    if [ -d "$HTML_REPORT_DIR" ]; then
        print_success "HTML report generated successfully"
        print_info "Report location: $HTML_REPORT_DIR"
        
        # Count files in report
        FILE_COUNT=$(find "$HTML_REPORT_DIR" -type f | wc -l)
        print_info "Report contains $FILE_COUNT files"
        
        # Display index.html location
        if [ -f "$HTML_REPORT_DIR/index.html" ]; then
            REPORT_PATH=$(cd "$HTML_REPORT_DIR" && pwd)
            print_success "Main report: $REPORT_PATH/index.html"
        fi
    else
        print_error "HTML report directory not found"
        exit 1
    fi
}

# Display summary
display_summary() {
    print_header "SUMMARY"
    
    echo ""
    echo "Test execution completed successfully!"
    echo ""
    echo "Results location: $(pwd)/$RESULTS_DIR"
    echo ""
    echo "Generated files:"
    echo "  • JTL Results: $JTL_FILE"
    echo "  • HTML Report: $HTML_REPORT_DIR/index.html"
    echo "  • JMeter Log: $LOG_FILE"
    echo ""
    echo "Next steps:"
    echo "  1. Review HTML report: open file://$HTML_REPORT_DIR/index.html"
    echo "  2. Check JTL file for detailed results"
    echo "  3. Commit results to GitHub"
    echo "  4. Push to Jenkins CI/CD pipeline"
    echo ""
}

# Display help
display_help() {
    echo "Usage: $0 [TEST_PLAN] [THREAD_COUNT] [RAMP_UP_TIME] [LOOP_COUNT]"
    echo ""
    echo "Parameters:"
    echo "  TEST_PLAN       - Path to JMeter test plan (.jmx file)"
    echo "                   Default: jmeter/HTTP Request.jmx"
    echo "  THREAD_COUNT    - Number of threads (default: 50)"
    echo "  RAMP_UP_TIME    - Ramp-up time in seconds (default: 1)"
    echo "  LOOP_COUNT      - Number of loops per thread (default: 1)"
    echo ""
    echo "Examples:"
    echo "  $0                                           # Run with defaults"
    echo "  $0 jmeter/JMeterCaseStudy.jmx               # Run specific test plan"
    echo "  $0 jmeter/HTTP\ Request.jmx 100 5 2        # Run with custom parameters"
    echo ""
}

# Main execution
main() {
    print_header "COMMONFLOOR JMETER LOCAL TEST RUNNER"
    
    # Show help if requested
    if [ "$1" == "-h" ] || [ "$1" == "--help" ]; then
        display_help
        exit 0
    fi
    
    # Verify environment
    check_jmeter
    check_test_plan
    setup_results_dir
    
    # Run tests
    run_test
    analyze_results
    check_report
    display_summary
}

# Run main function
main "$@"
