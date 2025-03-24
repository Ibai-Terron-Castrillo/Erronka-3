<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <title>Hasiera</title>
</head>

<body>
    <?PHP require_once("nav.php"); ?>
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 align-items-center my-5">
            <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="assets/cine.jpg" alt="..." /></div>
            <div class="card col-lg-5">
                <h1 class="font-weight-light">Ongi etorri El Umbral zinemara!</h1>
                <p>Non fikzio eta errealitatearen arteko lerroa desitxuratu egiten den</p>
                <?php if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
                    echo '<span class="welcome">Ongi etorri, ' . $_SESSION['izena'] . '!</span>';
                } else {
                    echo '<a class="btn btn-primary" href="login.php">Hasi saioa erreserbak egiteko</a>';
                }
                ?>
            </div>
        </div>
        <div class="card text-white bg-secondary my-5 py-4 text-center">
            <div class="card-body">
                <p class="text-white m-0">Ikus itzazu gure karteleran eskuragarri dauden filmak!
                </p>
            </div>
        </div>
        <div>
            <form class="form-inline" id="search-form">
                <input class="form-control" type="search" placeholder="Bilatu..." aria-label="Search" id="search">
                <select name="generoa" id="genero-search">
                    <option value="">Generoa</option>
                    <?PHP require_once("db.php");
                    $conn = konexioaSortu();
                    $sql = "SELECT DISTINCT generoa FROM pelikula";
                    $result = $conn->query($sql);
                    while ($row = $result->fetch_assoc()) {
                        $generoa = $row['generoa'];
                        echo "<option value='$generoa'>$generoa</option>";
                    }
                    ?>
                </select>
                <select name="sailkapena" id="sailkapena-search">
                    <option value="">Sailkapena</option>
                    <?PHP
                    $sql1 = "SELECT DISTINCT sailkapena FROM pelikula";
                    $result = $conn->query($sql1);
                    while ($row = $result->fetch_assoc()) {
                        $sailkapena = $row['sailkapena'];
                        echo "<option value='$sailkapena'>$sailkapena</option>";
                    }
                    ?>
                </select>
                <button class="btn btn-primary" id="search-button" type="submit">Bilatu</button>
            </form>
        </div>
        <div class="row gx-4 gx-lg-5" id="movies">

        </div>
        <div class="load-more"><button class="btn btn-primary btn-sm" id="load-more">Kargatu Gehiago</>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>