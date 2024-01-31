/**
 * 
 */
// custom.js
function updateSelectedPatient(patientSelectId, inputReadOnlyId, selectedPatientDivId) {
    var patientSelect = document.getElementById(patientSelectId);
    var inputReadOnly = document.getElementById(inputReadOnlyId);
    var selectedPatientDiv = document.getElementById(selectedPatientDivId);

    // Check if a patient has been selected
    if (patientSelect.value !== '') {
        var selectedPatientName = patientSelect.options[patientSelect.selectedIndex].text;

        // Update the input field
        inputReadOnly.value = selectedPatientName;

        // Update the content of the div outside the form
        selectedPatientDiv.innerText = 'Selected Patient: ' + selectedPatientName;

        // Show the details div (if needed)
        // detailsDiv.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', function () {
    // Trigger the updateSelectedPatient function manually on document ready
    updateSelectedPatient('patientSelect', 'patientName', 'selectedPatientDiv');
});
