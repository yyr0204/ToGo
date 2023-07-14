<!DOCTYPE html>
<!--
@license
Copyright 2019 Google LLC. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0
-->
<html>
<head>
    <title>Add Map</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/index.js"></script>
    <script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&libraries=drawing,geometry,maps&v=beta&callback=initMap"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h3>My Google Maps Demo</h3>
<!--The div element for the map -->
<div id="map" style="height: 600px"></div>

<!-- prettier-ignore -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>

</body>
</html>