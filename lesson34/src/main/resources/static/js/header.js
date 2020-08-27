document.addEventListener('DOMContentLoaded', function() {
    $('#langBox').change(function () {
        var selectedOption = $('#langBox').val();
        if (selectedOption !== ''){
            window.location.replace('?lang=' + selectedOption);
        }
    });
});
