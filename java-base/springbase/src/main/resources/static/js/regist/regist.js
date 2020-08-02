	$(function() {
		addSelectToStatus();
		addSelectToBehavior();
	})
	function addSelectToBehavior() {
		$("#behavior").empty();
		$('#behavior').append('<option value="">请选择</option>');
		$.ajax({
			url : '/getResltToBehavior',
			type : "post",
			dataType : "json",
			success : function(data) {
				$.each(data,
						function(index, item) {
							console.log(data[index]);
							da = data[index];
							var hi = $("#hidden-status").val();
							console.log(hi)
							if (da == null) {
								return;
							}
							if (da == hi) {
								$('#behavior').append(
										'<option selected="selected" value="'+da+'">'
												+ da + '</option>');
							} else {
								$('#behavior').append(
										'<option   value="'+da+'">' + da
												+ '</option>');
							}

						});
			},
			error : function() {
			}
		});
	}
	function addSelectToStatus() {
		$("#status").empty();
		$('#status').append('<option value="">请选择</option>');
		$.ajax({
			url : '/getResltToStatus',
			type : "post",
			dataType : "json",
			success : function(data) {
				$.each(data,
						function(index, item) {
							console.log(data[index]);
							da = data[index];
							var hi = $("#hidden-status").val();
							console.log(hi)
							if (da == null) {
								return;
							}
							if (da == hi) {
								$('#status').append(
										'<option selected="selected" value="'+da+'">'
												+ da + '</option>');
							} else {
								$('#status').append(
										'<option   value="'+da+'">' + da
												+ '</option>');
							}

						});
			},
			error : function() {
			}
		});
	}
	$("#test").chosen({
		disable_search_threshold : 10,
	});
	
function openSearch(){
	var text = $(".openSearch").val();
	if(text == "打开查询"){
		$(".openSearch").val("关闭查询");
		$(".form").toggle();//隐藏
	}else{
		$(".openSearch").val("打开查询");
		$(".form").toggle();//显示
	}
	
	
}