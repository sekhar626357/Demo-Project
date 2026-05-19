(function (document, $) {
  "use strict";

  $(document).on("foundation-contentloaded", function (e) {
    $(".primary-content-text-links .content-option", e.target).each(function (
      i,
      element
    ) {
      Coral.commons.ready(element, function (component) {
        toggleDisco(component.value);

        component.on("change", function () {
          toggleDisco(component.value);
        });
      });
    });
  });

  function toggleDisco(value) {
    var $disco = $(".primary-content-text-links .discoPromo-container");

    if (value === "disco_promo") {
      $disco.removeClass("hide");
    } else {
      $disco.addClass("hide");
    }
  }

})(document, Granite.$);
