window.initMap = function () {
    var lat = 35.0906115
    var lng = 129.0466149
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat, lng },
        zoom: 13,
    });
    const malls = [
        { label: "C", name: "닥밭골 벽화마을", lat: 35.114225, lng:129.0238337 },
        { label: "G", name: "자갈치 시장", lat: 35.0966559,lng: 129.0306748 },
        { label: "D", name: "태종대", lat: 35.0597537,lng:129.0802753},
        { label: "I", name: "송도", lat: 35.0753891,lng:129.016761 },
    ];
    const infowindow = new google.maps.InfoWindow();
    malls.forEach(({ label, name, lat, lng }) => {
        const marker = new google.maps.Marker({
            position: { lat, lng },
            label,
            map,
        });
        marker.addListener("click", () => {
            map.panTo(marker.position);
            infowindow.setContent(name);
            infowindow.open({
                anchor: marker,
                map,
            });
        });
    });
};

