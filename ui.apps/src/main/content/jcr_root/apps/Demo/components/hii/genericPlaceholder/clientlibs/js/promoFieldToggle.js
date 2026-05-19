(function (document, $) {
  'use strict';

  $(document).on('dialog-ready', function () {
    const placeholderType = $('[name="./placeholder"]');
    const promoId = $('[name="./promoId"]');
    const rteContainer = $('.cq-RichText.richtext-container');

    // Check if placeholderType and promoId are valid jQuery objects
    if (!placeholderType || placeholderType.length === 0 || !promoId || promoId.length === 0 || !rteContainer.length) {
      return; // Exit if either is not found
    }

    function togglePromoIdFieldVisibility(selectedValue) {
      const promoIdField = promoId.closest('.coral-Form-fieldwrapper')[0];
      if (!promoIdField) return;

      if (['disco_promo'].includes(selectedValue)) {
        promoIdField.style.display = ''; // Show the field
        rteContainer.show(); // show RTE
      } else {
        promoIdField.style.display = 'none';// Hide the field
        rteContainer.hide(); // Hide RTE
      }
    }

    setTimeout(function () {
      togglePromoIdFieldVisibility(placeholderType[0].value);
    }, 100);

    placeholderType[0]?.addEventListener('change', function(event) {
      togglePromoIdFieldVisibility(placeholderType[0].value);
    });
  });
})(document, Granite.$);
