let buttonCancel = document.querySelector("#btn-cancel");
buttonCancel.addEventListener("click", homepage);

/**
 * Lien vers la page de modification d'un nouveau festival.
 * @param {Event} event
 */
function homepage(event) {
    console.log("salut");
    document.location.href = "/festivals";
}