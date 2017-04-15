/**
 * 
 */


$(function() {
    // show reportList
    function showReportList() {
        var proxy = "getMailList";
        $
                .getJSON(
                        proxy,
                        function(result) {
                            if (null != result && 200 == result.code) {
                                var data = new Array();
                                var index = 0;
                                for ( var d in result.data) {
                                    var item = new Array()
                                    item[0] = result.data[d].id; // 报告编码
                                    item[1] = result.data[d].email; // 报告名称
                                    item[2] = result.data[d].ownerName; // 项目地址
                                    var unixTimestamp = new Date(result.data[d].timeStamp)
                                    item[3] = unixTimestamp.toLocaleString(); // 风险等级
                                    data[index++] = item;
                                }
                                $("#emailListTable").dataTable({
                                    "data" : data,
                                    language : {
                                        url : "css/datatables/Chinese.json"
                                    }
                                });
                            }
                        });
    }

    showReportList();

});