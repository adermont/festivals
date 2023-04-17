// La map Leaflet
let mymap = L.map('map').setView([48.202047, -2.932644], 8);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    tileSize: 512, zoomOffset: -1, maxZoom: 18
}).addTo(mymap);

let tempMarker;

mymap.on('click', event => {
    if (tempMarker) {
        mymap.removeLayer(tempMarker);
    }
    tempMarker = L.marker(event.latlng).addTo(mymap);
    document.getElementById('lat').value = event.latlng.lat;
    document.getElementById('lon').value = event.latlng.lng;

    // Interrogation de l'API nominatim en mode 'reverse' pour obtenir les infos de la localisation
    // https://nominatim.org/release-docs/develop/api/Output/
    //
    let request = `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${event.latlng.lat}&lon=${event.latlng.lng}`;
    console.log(request);

    fetch(request)
        .then(json => {
            json.json().then(geodata => {
                document.getElementById('cp').value = geodata.address.postcode;
                if (geodata.address.hamlet) {
                    document.getElementById('lieu').value = geodata.address.hamlet;
                } else if (geodata.display_name) {
                    document.getElementById('lieu').value = geodata.display_name;
                } else if (geodata.address.road) {
                    document.getElementById('lieu').value = geodata.address.road;
                }
                if (geodata.address.city) {
                    document.getElementById('ville').value = geodata.address.city;
                } else if (geodata.address.village) {
                    document.getElementById('ville').value = geodata.address.village;
                } else if (geodata.address.hamlet) {
                    document.getElementById('ville').value = geodata.address.hamlet;
                }
            }).catch(err => {
                console.error(err);
            });
        })
        .catch(err => {
            console.error(err);
        });
});
