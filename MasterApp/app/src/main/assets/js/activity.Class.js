var fhBtn=document.querySelector("#fhBtn");
var dialog = new guiDialog();
function back(){
        //jsMethod_back();
        $z.app.back();
    }


$(function(){
$("#nldsl").click(function(){
        					 window.ZJEnergyBean.transferData();
        					 });
	$('.activity').each(function() {
		var stime = $(this).find('.startime').text();
		var etime = $(this).find('.endtime').text();
		time1 = stime.replace(/\-/gi,"/");
		time2 = etime.replace(/\-/gi,"/");
		var nowTime = new Date().getTime();
		prevtime = new Date(time1).getTime();
		nexttime= new Date(time2).getTime()+86400000;
		var activity_status='';
		if(prevtime>nowTime){
		   activity_status='还未开始';
		}else if(prevtime<nowTime&&nowTime<nexttime){
		   activity_status='火爆进行中...';
		}else if(nowTime>=nexttime){
		   activity_status='已结束';
		}
		$(this).find('.activity_status').html(activity_status);
	});
	 $("#nldsl").click(function(){
//					 window.ZJEnergyBean.transferData()
   alert('您的鼠标移到了 id="p1" 的元素上!');
   console.log("wanghaitao123")
					});
});

$(document).on("click", ".activity" ,function(){
    var stime = $(this).find('.startime').text();
	var etime = $(this).find('.endtime').text();
	var url = $(this).find('.activity_url').val();
	console.log($(this).find(".startime"));
	var nowTime = new Date().getTime();
	time1 = stime.replace(/\-/gi,"/");
	time2 = etime.replace(/\-/gi,"/");
	prevtime = new Date(time1).getTime();
	nexttime= new Date(time2).getTime()+86400000;
	if(prevtime>nowTime){
		 dialog.alert({
                title:"",
                msg:'活动还未开始，敬请期待！',
                buttons:['确定']
            })
	}
	else if(prevtime<nowTime&&nowTime<(nexttime)){
		if(url=="nld"){
			 if(iosand() == "ios") {
                    $z.app.jump('ZJEnergyViewController', 1, {});
                }else{
                    $z.app.jump('com.sgcc.cs.area.zhejiang.ZJEnergyBeanActivity', 1, {});
					}

		}else{
			window.location.href=url;
			}
            window.ZJEnergyBean.transferData();
	}
	else if(nowTime>nexttime){
		dialog.alert({
                title:"",
                msg:'活动已结束，敬请期待下期活动谢谢',
                buttons:['确定']
            })
	}
  })


