(function(document, $) {
    "use strict";

    $(document).on("click", ".cq-dialog-submit", function(e) {
        const fields = $("form.cq-dialog").find("[name^='./']");
        let missingFields = [];

        fields.each(function() {
            const $field = $(this);
            const value = $field.val();
            const label = $field.closest(".coral-Form-fieldwrapper").find("label").text().trim();

            if (!value) {
                missingFields.push(label || $field.attr("name"));
            }
        });

        if (fields.length > 0 && missingFields.length === fields.length) {
            e.preventDefault();
            Coral.commons.ready(function() {
                Coral.Dialog.alert("Validation", "None of the fields are authored in the cq:dialog.");
            });
        } else if (missingFields.length > 0) {
            e.preventDefault();
            Coral.commons.ready(function() {
                Coral.Dialog.alert("Validation", "The following fields are missing: <br><ul><li>" +
                    missingFields.join("</li><li>") + "</li></ul>");
            });
        }
    });
})(document, Granite.$);
