(function (document, $, Coral) {
    "use strict";

    $(document).on("foundation-contentloaded", function (e) {

        $(".cq-dialog-dropdown-showhide", e.target).each(function () {

            var element = this;
            var target = $(element).data("cqDialogDropdownShowhideTarget");

            if (!target) {
                console.log("Showhide target not found");
                return;
            }

            Coral.commons.ready(element, function (component) {

                showHide(component, target);

                component.on("change", function () {
                    showHide(component, target);
                });

            });

        });

    });

    function showHide(component, target) {

        var value = component.value;

        // Hide all first
        $(target).addClass("hide");

        // Show only the selected one
        if (value) {
            $(target)
                .filter("[data-showhidetargetvalue='" + value + "']")
                .removeClass("hide");
        }
    }

})(document, Granite.$, Coral);