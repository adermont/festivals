let buttonAjouterFestival = document.getElementById("btn-ajouter-festival");
buttonAjouterFestival.addEventListener("click", ajouterFestival);

let buttonsEditFestival = document.querySelectorAll(".action-edit-festival");
buttonsEditFestival.forEach(b => b.addEventListener("click", event => editerFestival(event)));

/**
 * Lien vers la page de création d'un nouveau festival.
 * @param {Event} event
 */
function ajouterFestival(event) {
    document.location.href = "/ajouter-festival";
}

/**
 * Lien vers la page de modification d'un nouveau festival.
 * @param {Event} event
 */
function editerFestival(event) {
    const buttonId = event.target.id;
    const festivalId = buttonId.substring(buttonId.lastIndexOf("-"));
    document.location.href = `/editer-festival?festivalId=${festivalId}`;
}

