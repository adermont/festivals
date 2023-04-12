// La map Leaflet
let mymap = L.map('map').setView([48.202047, -2.932644], 8);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    tileSize: 512,
    zoomOffset: -1,
    maxZoom: 18
}).addTo(mymap);

let tempMarker;

mymap.on('click', function (e) {
    if (tempMarker){
        mymap.removeLayer(tempMarker);
    }
    tempMarker = L.marker(e.latlng).addTo(mymap);
    document.getElementById('lat').value = e.latlng.lat;
    document.getElementById('lon').value = e.latlng.lng;
});
