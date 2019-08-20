function getCreateView() {
	$('#container').hide();
	console.log("getCreateView()");
	$.ajax({
		type : "POST",
		url : "caseReqCreate",
		success : function(data) {
			$('#CreateView').show();
			$('#CreateView').html(data);

			// $('#startdate,#enddate').mask('0000/00/00');

			$("#retun").click(function(e) {
				console.log("return()");
				$('#CreateView').hide();
				$('#container').show();
			});
		},
		error : function(data) {
			alert('發生錯誤');
		}
	});
}

function saveCaseReq() {
	console.log("save()");
	var form = $('#caseReqCreateOrUpdateForm');
	var url = form.attr('action');
	$.ajax({
		type : "POST",
		url : url,
		data : form.serialize(), // serializes the form's elements.
		success : function(data) {
			console.log(data);

			if (data.msg == 'error') {
				alert(data.date.errorMsg);
			}
			if (data.msg == 'success') {
				console.log(data.date);
				alert('新增成功');
				$('#CreateView').hide();
				$('#container').show();
				queryCaseReqPageable();
			}

		},
		error : function(data) {
			alert('發生錯誤');
		}
	});
}

function caseReqUpdate() {
	console.log("Update()");
	var form = $('#caseReqCreateOrUpdateForm');
	var url = form.attr('action');
	$.ajax({
		type : "POST",
		url : url,
		data : form.serialize(), // serializes the form's elements.
		success : function(data) {

			console.log(data);
//			var obj = JSON.parse(data);
			if (data.msg == 'error') {
				alert(data.date.errorMsg);
			}
			if (data.msg == 'success') {
				alert('修改成功');
				$('#CreateView').hide();
				$('#container').show();
				queryCaseReqPageable();
			}

		},
		error : function(data) {
			alert('發生錯誤');
		}
	});
}

function getOneForUpdate(e) {
	// $('#page').load("/oa/caseReq/caseReqUpdate?caseNo=" + $(e).val());

	$('#container').hide();
	console.log("getOneForUpdate()");
	$.ajax({
		type : "POST",
		url : "caseReqUpdate?caseNo=" + $(e).val(),
		success : function(data) {
			$('#CreateView').show();
			$('#CreateView').html(data);
			$("#retun").click(function(e) {
				console.log("return()");
				$('#CreateView').hide();
				$('#container').show();
			});
		},
		error : function(data) {
			alert('發生錯誤');
		}
	});

}

function returnCaseReq() {
	$('#page2').hide();
	$('#page').show();
}

function queryCaseReqPageable(e) {
	console.log("queryCaseReqPageable()");
	var form = $('#caseReqSearchForm');
	// var url = 'caseReqSearchResult';

	$.ajax({
		type : "POST",
		url : 'caseReqSearchResult',
		data : form.serialize(), // serializes the form's elements.
		success : function(data) {
			$('#queryResult').html(data);
			$(".page-link").click(function(e) {
				e.preventDefault();
				Pageable(this.href);
			});
			$(".sorted").click(function(e) {
				e.preventDefault();
				Pageable(this.href);
			});
		},
		error : function(data) {
			alert('發生錯誤');
		}
	});

}

function Pageable(e) {

	$('#queryResult').load(e);
	$(".page-link").click(function(e) {
		e.preventDefault();
	});
}

function selectAllDeleteItems(e) {
	if ($(e).prop("checked")) {
		$(".deleteItems").each(function() {
			$(this).prop("checked", true);
		});
	} else {
		$(".deleteItems").each(function() {
			$(this).prop("checked", false);
		});
	}
}

function deleteCaseReqs() {
	var caseNos = [];
	$(".deleteItems").each(function() {
		if ($(this).prop("checked"))
			caseNos.push($(this).val());
	});
	console.log(caseNos.length);
	if (caseNos.length != 0) {
		var msg = '您真的確定要刪除嗎？' + '\n' + '\n請確認！';
		if (confirm(msg) == true) {

			console.log(msg);
			$.ajax({
				url : "deleteCaseReq",
				type : "POST",
				data : {
					caseNos : caseNos
				},
				success : function(data) {
					console.log("#" + data);
					alert(data);
					queryCaseReqPageable();
				},
				error : function(data) {
					alert('發生錯誤');
				}
			});
			queryCaseReqPageable();

			return true;

		} else {

			return false;
		}

	}

}
function uncheckedSelectAllDeleteItems() {
	$("#selectAllDeleteItemsCheckbox").prop("checked", false);
}

function getCaseReqJasper() {
	console.log("converPDF()");
	var form = $('#caseReqSearchForm');
	var url = "converPDF";
	$.ajax({
		type : "POST",
		url : url,
		data : form.serialize(),// serializes the form's elements.

	// success : function(data) {

	// console.log("return2()");

	// $('#CreateView').hide();

	// $('#container').show();

	// queryCaseReqPageable();

	// }

	});
}
