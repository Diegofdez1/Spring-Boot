$(document).ready(function () {

    $(".delete-button").on("click", function () {
        var modal = $("#delete-modal")
        modal.find("div.modal-body").find("input").remove();
        var name = $(this).attr("name");
        var id = $(this).attr("id");
        modal.find("#body-text-confirm-modal").text("¿Estás seguro que quieres eliminar el miembro con nombre " + name + "?");
        modal.find("div.modal-body").append('<input type="hidden" name="id" value="' + id + '" />');
        modal.modal('show');
    });

});