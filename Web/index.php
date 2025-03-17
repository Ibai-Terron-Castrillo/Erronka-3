<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <title>El Umbral - Hasiera</title>
</head>

<body>
    <!-- navbar-->
    <?PHP require_once("nav.php"); ?>
    <!-- Page Content-->
    <div class="container px-4 px-lg-5">
        <!-- Heading Row-->
        <div class="row gx-4 gx-lg-5 align-items-center my-5">
            <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="assets/cine.jpg" alt="..." /></div>
            <div class="card col-lg-5">
                <h1 class="font-weight-light">Ongi etorri El Umbral zinemara!</h1>
                <p>Non fikzioaren eta errealitatearen arteko lerroa desitxuratu egiten den</p>
                <a class="btn btn-primary" href="#!">Hasi saioa erreserbak egiteko</a>
            </div>
        </div>
        <!-- Call to Action-->
        <div class="card text-white bg-secondary my-5 py-4 text-center">
            <div class="card-body">
                <p class="text-white m-0">Ikus itzazu gure karteleran eskuragarri dauden filmak!
                </p>
            </div>
        </div>
        <!-- Content Row-->
        <div class="row gx-4 gx-lg-5" id="movies">

        </div>
        <div class="load-more"><button class="btn btn-primary btn-sm" id="load-more">Kargatu Gehiago</>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>