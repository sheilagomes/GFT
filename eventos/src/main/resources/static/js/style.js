$(".alert").delay(4000).slideUp(200, function() {
    $(this).alert('close');
});

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})

$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})