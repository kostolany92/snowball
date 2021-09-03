function addFormData(formId, name, value) {
	$('#' + formId).append('<input type="hidden" name="' + name +'" value="' + value + '" class="' + formId + '"/>');
}

function getContextPath() {
	var offset = location.href.indexOf(location.host)+location.host.length;
    var ctxPath = location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}