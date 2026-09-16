/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 74.92572786690434, "KoPercent": 25.07427213309566};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.654486036838978, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "Ikea - Request 1"], "isController": false}, {"data": [0.5166666666666667, 500, 1500, "Walmart - Request 5"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 2"], "isController": false}, {"data": [0.45, 500, 1500, "Walmart - Request 4"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 3"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 4"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 5"], "isController": false}, {"data": [0.4666666666666667, 500, 1500, "Walmart - Request 1"], "isController": false}, {"data": [0.5166666666666667, 500, 1500, "Walmart - Request 3"], "isController": false}, {"data": [0.5166666666666667, 500, 1500, "Walmart - Request 2"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 5-2"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 3-1"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 1-2"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 3-0"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 5-1"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 3-2"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 5-0"], "isController": false}, {"data": [1.0, 500, 1500, "Walmart - Request 5-2"], "isController": false}, {"data": [0.9666666666666667, 500, 1500, "Walmart - Request 3-2"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 1-1"], "isController": false}, {"data": [1.0, 500, 1500, "Walmart - Request 5-0"], "isController": false}, {"data": [0.0, 500, 1500, "Zomato - Request 1"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 1-0"], "isController": false}, {"data": [0.7833333333333333, 500, 1500, "Walmart - Request 5-1"], "isController": false}, {"data": [0.9833333333333333, 500, 1500, "Walmart - Request 1-2"], "isController": false}, {"data": [0.9833333333333333, 500, 1500, "Swiggy - Request 4-0"], "isController": false}, {"data": [0.9833333333333333, 500, 1500, "Walmart - Request 3-0"], "isController": false}, {"data": [0.9833333333333333, 500, 1500, "Swiggy - Request 2-1"], "isController": false}, {"data": [0.75, 500, 1500, "Walmart - Request 3-1"], "isController": false}, {"data": [1.0, 500, 1500, "Walmart - Request 1-0"], "isController": false}, {"data": [1.0, 500, 1500, "Swiggy - Request 2-0"], "isController": false}, {"data": [0.7, 500, 1500, "Walmart - Request 1-1"], "isController": false}, {"data": [0.8833333333333333, 500, 1500, "Swiggy - Request 4-2"], "isController": false}, {"data": [1.0, 500, 1500, "Swiggy - Request 4-1"], "isController": false}, {"data": [0.0, 500, 1500, "Swiggy - Request 5"], "isController": false}, {"data": [0.6333333333333333, 500, 1500, "Swiggy - Request 4"], "isController": false}, {"data": [0.6833333333333333, 500, 1500, "Swiggy - Request 1"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 2-2"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 4-0"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 2-1"], "isController": false}, {"data": [0.0, 500, 1500, "Swiggy - Request 3"], "isController": false}, {"data": [0.0, 500, 1500, "Ikea - Request 4-2"], "isController": false}, {"data": [0.85, 500, 1500, "Swiggy - Request 2"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 4-1"], "isController": false}, {"data": [0.9833333333333333, 500, 1500, "Walmart - Request 4-1"], "isController": false}, {"data": [1.0, 500, 1500, "Ikea - Request 2-0"], "isController": false}, {"data": [0.5, 500, 1500, "Walmart - Request 4-2"], "isController": false}, {"data": [0.0, 500, 1500, "Swiggy - Request 3-1"], "isController": false}, {"data": [0.8333333333333334, 500, 1500, "Walmart - Request 2-1"], "isController": false}, {"data": [0.9666666666666667, 500, 1500, "Swiggy - Request 3-0"], "isController": false}, {"data": [0.95, 500, 1500, "Walmart - Request 2-2"], "isController": false}, {"data": [1.0, 500, 1500, "Walmart - Request 4-0"], "isController": false}, {"data": [0.9, 500, 1500, "Swiggy - Request 1-1"], "isController": false}, {"data": [0.9166666666666666, 500, 1500, "Swiggy - Request 1-0"], "isController": false}, {"data": [1.0, 500, 1500, "Walmart - Request 2-0"], "isController": false}, {"data": [0.0, 500, 1500, "Swiggy - Request 5-1"], "isController": false}, {"data": [0.0, 500, 1500, "Zomato - Request 1-1"], "isController": false}, {"data": [1.0, 500, 1500, "Swiggy - Request 5-0"], "isController": false}, {"data": [0.5, 500, 1500, "Zomato - Request 1-0"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1683, 422, 25.07427213309566, 658.2840166369581, 6, 63886, 293.0, 1708.0000000000007, 1917.3999999999996, 2693.080000000001, 25.005200130746143, 2664.2078250824593, 4.566858127432918], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Ikea - Request 1", 30, 30, 100.0, 1982.0333333333333, 1577, 2457, 1999.0, 2289.6, 2397.6, 2457.0, 1.1604966925844262, 527.6616021865692, 0.40118733317860045], "isController": false}, {"data": ["Walmart - Request 5", 30, 0, 0.0, 823.5333333333333, 472, 1934, 752.5, 1108.5, 1603.4499999999996, 1934.0, 1.6195206218959188, 28.481790093257395, 0.6911430778989419], "isController": false}, {"data": ["Ikea - Request 2", 30, 30, 100.0, 1806.2333333333331, 1361, 2524, 1808.0, 2156.5, 2339.75, 2524.0, 1.2066122350480635, 548.6366067298798, 0.4241996138840848], "isController": false}, {"data": ["Walmart - Request 4", 30, 0, 0.0, 1262.5666666666666, 278, 3113, 1134.5, 2627.4000000000005, 3029.95, 3113.0, 1.6680567139282736, 627.4469718689186, 0.5701365721434529], "isController": false}, {"data": ["Ikea - Request 3", 30, 30, 100.0, 1743.9, 1183, 2963, 1694.0, 2035.0000000000002, 2483.9499999999994, 2963.0, 1.208313194780087, 549.4135983692806, 0.4283375876027066], "isController": false}, {"data": ["Ikea - Request 4", 30, 30, 100.0, 1783.7333333333331, 1025, 3785, 1725.5, 2731.700000000001, 3245.999999999999, 3785.0, 1.2369603760359544, 562.4282869000742, 0.4276210674968045], "isController": false}, {"data": ["Ikea - Request 5", 30, 30, 100.0, 1848.766666666667, 1119, 2802, 1774.5, 2590.4000000000005, 2794.85, 2802.0, 1.2527142141306162, 569.5991216646484, 0.4404073409052948], "isController": false}, {"data": ["Walmart - Request 1", 30, 0, 0.0, 873.4333333333332, 525, 1722, 788.5, 1301.2, 1718.15, 1722.0, 1.8782870022539444, 33.036023490326826, 0.7868995351239669], "isController": false}, {"data": ["Walmart - Request 3", 30, 0, 0.0, 825.5666666666666, 451, 1301, 844.5, 1067.7, 1286.7, 1301.0, 1.8788751800588714, 33.040558260787876, 0.80549433988852], "isController": false}, {"data": ["Walmart - Request 2", 30, 0, 0.0, 815.4333333333334, 474, 1300, 819.0, 1083.6, 1237.3, 1300.0, 1.9180359312064446, 34.11269360175181, 0.8185368182980628], "isController": false}, {"data": ["Ikea - Request 5-2", 30, 30, 100.0, 1732.0333333333335, 1085, 2704, 1617.5, 2443.100000000001, 2696.85, 2704.0, 1.259022998153433, 571.0761515470246, 0.15000078688937385], "isController": false}, {"data": ["Ikea - Request 3-1", 30, 0, 0.0, 65.89999999999998, 14, 235, 43.5, 136.50000000000009, 224.0, 235.0, 1.3054262216613723, 0.8970980783038162, 0.15552929594012443], "isController": false}, {"data": ["Ikea - Request 1-2", 30, 30, 100.0, 1789.366666666667, 1481, 2246, 1730.0, 2156.2, 2213.55, 2246.0, 1.1939348111593107, 541.5529733951527, 0.13991423568273173], "isController": false}, {"data": ["Ikea - Request 3-0", 30, 0, 0.0, 68.96666666666667, 9, 179, 64.5, 132.0, 163.04999999999998, 179.0, 1.3011233031183589, 0.5476407652773562, 0.14993413063277963], "isController": false}, {"data": ["Ikea - Request 5-1", 30, 0, 0.0, 46.36666666666666, 12, 183, 27.5, 133.90000000000006, 165.95, 183.0, 1.34390538906061, 0.9210039113246428, 0.15880132038704475], "isController": false}, {"data": ["Ikea - Request 3-2", 30, 30, 100.0, 1608.7333333333333, 1058, 2915, 1595.0, 1805.2, 2319.899999999999, 2915.0, 1.2159533073929962, 551.5401125263456, 0.14605689141536965], "isController": false}, {"data": ["Ikea - Request 5-0", 30, 0, 0.0, 70.23333333333335, 6, 231, 50.0, 177.70000000000002, 226.6, 231.0, 1.3393455064958257, 0.5624204763605518, 0.15303068775391757], "isController": false}, {"data": ["Walmart - Request 5-2", 30, 0, 0.0, 175.53333333333336, 53, 462, 149.5, 353.40000000000003, 406.99999999999994, 462.0, 1.687858669967368, 26.276640915100707, 0.3181218001012715], "isController": false}, {"data": ["Walmart - Request 3-2", 30, 0, 0.0, 189.50000000000006, 53, 591, 130.0, 373.8, 542.05, 591.0, 1.9474196689386563, 30.31692480120091, 0.36704296494644595], "isController": false}, {"data": ["Ikea - Request 1-1", 30, 0, 0.0, 86.2666666666667, 12, 428, 57.0, 183.80000000000004, 394.44999999999993, 428.0, 1.2565971349585323, 0.8561386078998073, 0.14603033111334507], "isController": false}, {"data": ["Walmart - Request 5-0", 30, 0, 0.0, 108.89999999999998, 26, 268, 91.5, 252.9, 268.0, 268.0, 1.6786034019695613, 1.1269373880931066, 0.19671133616830797], "isController": false}, {"data": ["Zomato - Request 1", 1, 1, 100.0, 63886.0, 63886, 63886, 63886.0, 63886.0, 63886.0, 63886.0, 0.015652881695520145, 0.06279495898944995, 0.0017884640218514228], "isController": false}, {"data": ["Ikea - Request 1-0", 30, 0, 0.0, 106.13333333333333, 13, 407, 72.5, 268.6000000000001, 378.95, 407.0, 1.242338910054663, 0.5192588413119099, 0.13952048306277953], "isController": false}, {"data": ["Walmart - Request 5-1", 30, 0, 0.0, 538.8999999999999, 325, 1405, 483.0, 793.5, 1152.5499999999997, 1405.0, 1.6516185862144903, 2.2250076559403213, 0.2000006881744109], "isController": false}, {"data": ["Walmart - Request 1-2", 30, 0, 0.0, 169.66666666666669, 56, 529, 139.5, 303.6, 425.04999999999984, 529.0, 2.0107238605898123, 31.302689866789546, 0.3711199313002681], "isController": false}, {"data": ["Swiggy - Request 4-0", 30, 0, 0.0, 168.16666666666663, 52, 517, 133.5, 415.40000000000003, 496.09999999999997, 517.0, 2.883229216722729, 0.9516908938010572, 0.3294314632388275], "isController": false}, {"data": ["Walmart - Request 3-0", 30, 0, 0.0, 97.49999999999997, 28, 520, 74.0, 148.30000000000004, 383.04999999999984, 520.0, 1.979283499373227, 1.3316995653823316, 0.23388017912515668], "isController": false}, {"data": ["Swiggy - Request 2-1", 30, 0, 0.0, 298.8, 154, 658, 260.5, 422.20000000000005, 530.3999999999999, 658.0, 2.983887010145216, 248.996434876666, 0.37007192410980705], "isController": false}, {"data": ["Walmart - Request 3-1", 30, 0, 0.0, 538.1999999999999, 361, 927, 499.0, 684.9, 854.9499999999999, 927.0, 1.9126554032515142, 2.57199852566146, 0.23347844277972585], "isController": false}, {"data": ["Walmart - Request 1-0", 30, 0, 0.0, 143.33333333333334, 27, 436, 92.5, 359.60000000000014, 410.7, 436.0, 1.9760242392306682, 1.3246823952707152, 0.2277059181925965], "isController": false}, {"data": ["Swiggy - Request 2-0", 30, 0, 0.0, 157.83333333333331, 53, 455, 134.5, 346.0000000000001, 434.65, 455.0, 3.043522369889419, 1.010544536877346, 0.35369058790707114], "isController": false}, {"data": ["Walmart - Request 1-1", 30, 0, 0.0, 559.9, 350, 1223, 528.0, 901.1000000000005, 1112.4499999999998, 1223.0, 1.9559264571652104, 2.6407553950971443, 0.23303030056069893], "isController": false}, {"data": ["Swiggy - Request 4-2", 30, 0, 0.0, 358.7333333333333, 179, 645, 329.5, 561.3000000000001, 607.05, 645.0, 2.8779739063699155, 246.91938750119917, 0.36255725968917885], "isController": false}, {"data": ["Swiggy - Request 4-1", 30, 0, 0.0, 197.96666666666667, 122, 416, 171.5, 323.7000000000001, 386.84999999999997, 416.0, 2.867520550563946, 2.1761232615656665, 0.3500391297075129], "isController": false}, {"data": ["Swiggy - Request 5", 30, 30, 100.0, 339.9333333333333, 116, 661, 321.0, 568.2, 658.25, 661.0, 2.914319020788809, 13.23434767825918, 0.7001196085098115], "isController": false}, {"data": ["Swiggy - Request 4", 30, 0, 0.0, 725.2666666666668, 421, 1262, 678.5, 1111.6000000000004, 1258.7, 1262.0, 2.7891409445890667, 242.33512835859054, 1.0105188383227965], "isController": false}, {"data": ["Swiggy - Request 1", 30, 0, 0.0, 705.6333333333333, 190, 2585, 600.0, 1260.0, 1900.7999999999993, 2585.0, 2.8071488724618696, 248.67217837676617, 0.6634082296247779], "isController": false}, {"data": ["Ikea - Request 2-2", 30, 30, 100.0, 1673.5333333333333, 1277, 2248, 1669.5, 1931.9, 2155.6, 2248.0, 1.2082645293809657, 548.0525859378147, 0.14395339119577913], "isController": false}, {"data": ["Ikea - Request 4-0", 30, 0, 0.0, 60.09999999999999, 6, 233, 47.5, 145.20000000000002, 193.94999999999996, 233.0, 1.2953367875647668, 0.5414102979274611, 0.14547239313471502], "isController": false}, {"data": ["Ikea - Request 2-1", 30, 0, 0.0, 58.53333333333334, 13, 214, 34.0, 145.00000000000006, 203.0, 214.0, 1.2728044123886295, 0.8721527630462452, 0.1503997401357658], "isController": false}, {"data": ["Swiggy - Request 3", 30, 30, 100.0, 344.3999999999999, 125, 701, 286.5, 644.7, 681.1999999999999, 701.0, 2.8735632183908044, 13.060307411398467, 0.7127783764367817], "isController": false}, {"data": ["Ikea - Request 4-2", 30, 30, 100.0, 1676.3, 998, 3709, 1555.5, 2636.7000000000016, 3174.9499999999994, 3709.0, 1.2524527199098234, 568.0956749937377, 0.14677180311443244], "isController": false}, {"data": ["Swiggy - Request 2", 30, 0, 0.0, 456.83333333333337, 214, 822, 418.5, 693.4000000000002, 819.25, 822.0, 2.963548355230663, 248.2832256989035, 0.7119461869011163], "isController": false}, {"data": ["Ikea - Request 4-1", 30, 0, 0.0, 47.133333333333326, 12, 146, 32.5, 114.40000000000003, 141.6, 146.0, 1.3028750108572917, 0.8876260124424563, 0.15140832645704855], "isController": false}, {"data": ["Walmart - Request 4-1", 30, 0, 0.0, 93.73333333333332, 25, 613, 54.0, 204.20000000000005, 438.0999999999998, 613.0, 1.9557989438685706, 1.5231930251320165, 0.21773542929786818], "isController": false}, {"data": ["Ikea - Request 2-0", 30, 0, 0.0, 74.0, 6, 260, 65.0, 177.30000000000007, 251.75, 260.0, 1.27269641948074, 0.5344330667741388, 0.14541550886645171], "isController": false}, {"data": ["Walmart - Request 4-2", 30, 0, 0.0, 1085.0333333333335, 220, 2750, 993.0, 2421.9000000000005, 2655.95, 2750.0, 1.677570877369569, 628.4254774087123, 0.19331383157188392], "isController": false}, {"data": ["Swiggy - Request 3-1", 30, 30, 100.0, 153.3333333333333, 58, 504, 124.0, 317.6, 415.4499999999999, 504.0, 2.902195994969527, 12.215486541066074, 0.3712770267002032], "isController": false}, {"data": ["Walmart - Request 2-1", 30, 0, 0.0, 482.5999999999999, 350, 796, 440.5, 685.8, 736.05, 796.0, 1.9464088756244728, 2.7949214948420162, 0.23569794978265102], "isController": false}, {"data": ["Swiggy - Request 3-0", 30, 0, 0.0, 190.9666666666667, 55, 525, 160.0, 376.40000000000003, 520.6, 525.0, 2.9075402209730568, 0.9767517929831363, 0.3492455538864121], "isController": false}, {"data": ["Walmart - Request 2-2", 30, 0, 0.0, 252.30000000000004, 48, 670, 207.0, 535.7000000000002, 634.25, 670.0, 1.967600183642684, 30.63108982504755, 0.3708465189873418], "isController": false}, {"data": ["Walmart - Request 4-0", 30, 0, 0.0, 83.53333333333333, 26, 303, 55.0, 260.5000000000001, 284.29999999999995, 303.0, 1.9543973941368078, 1.5072780944625408, 0.2252137622149837], "isController": false}, {"data": ["Swiggy - Request 1-1", 30, 0, 0.0, 403.13333333333327, 139, 1666, 349.5, 731.5000000000002, 1177.0499999999993, 1666.0, 2.9670655721491443, 261.8590867928494, 0.36219062160023735], "isController": false}, {"data": ["Swiggy - Request 1-0", 30, 0, 0.0, 302.10000000000014, 51, 918, 200.0, 625.8000000000001, 909.2, 918.0, 2.864235249188467, 0.9454214006110369, 0.3272612540576666], "isController": false}, {"data": ["Walmart - Request 2-0", 30, 0, 0.0, 80.26666666666668, 26, 324, 68.5, 115.80000000000001, 262.94999999999993, 324.0, 2.0196580045778916, 1.5784495127575064, 0.23667867241147167], "isController": false}, {"data": ["Swiggy - Request 5-1", 30, 30, 100.0, 172.06666666666666, 55, 499, 129.0, 364.0, 496.8, 499.0, 2.931691586045148, 12.339825808658262, 0.3635984681911463], "isController": false}, {"data": ["Zomato - Request 1-1", 1, 1, 100.0, 62985.0, 62985, 62985, 62985.0, 62985.0, 62985.0, 62985.0, 0.015876796062554575, 0.05845265737874097, 0.0], "isController": false}, {"data": ["Swiggy - Request 5-0", 30, 0, 0.0, 167.8, 53, 498, 124.0, 334.0, 412.1999999999999, 498.0, 2.9582881372645695, 0.9822441080761266, 0.34378543782664434], "isController": false}, {"data": ["Zomato - Request 1-0", 1, 0, 0.0, 901.0, 901, 901, 901.0, 901.0, 901.0, 901.0, 1.1098779134295227, 0.3663464206437292, 0.12681222253052163], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["Non HTTP response code: java.net.SocketException/Non HTTP response message: Operation timed out", 2, 0.47393364928909953, 0.11883541295306001], "isController": false}, {"data": ["404/Not Found", 420, 99.5260663507109, 24.955436720142604], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1683, 422, "404/Not Found", 420, "Non HTTP response code: java.net.SocketException/Non HTTP response message: Operation timed out", 2, "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Ikea - Request 1", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["Ikea - Request 2", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["Ikea - Request 3", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Ikea - Request 4", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Ikea - Request 5", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Ikea - Request 5-2", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["Ikea - Request 1-2", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Ikea - Request 3-2", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Zomato - Request 1", 1, 1, "Non HTTP response code: java.net.SocketException/Non HTTP response message: Operation timed out", 1, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Swiggy - Request 5", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Ikea - Request 2-2", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Swiggy - Request 3", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Ikea - Request 4-2", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Swiggy - Request 3-1", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Swiggy - Request 5-1", 30, 30, "404/Not Found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Zomato - Request 1-1", 1, 1, "Non HTTP response code: java.net.SocketException/Non HTTP response message: Operation timed out", 1, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
