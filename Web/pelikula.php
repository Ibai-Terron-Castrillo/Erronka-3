<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <?PHP require_once("db.php");
    $conn = konexioaSortu();
    if (isset($_GET['id'])) {
        $id = intval($_GET['id']);
        $sql = "SELECT 
        `pelikula`.`izena`,
    `pelikula`.`iraunaldia`,
    `pelikula`.`generoa`,
    `pelikula`.`sailkapena`,
    `pelikula`.`sinopsia`,
    `pelikula`.`aktoreak`,
    `pelikula`.`zuzendaria`,
    `pelikula`.`kartela`,
    `pelikula`.`trailerra`
FROM `erronka3`.`pelikula` WHERE id = $id";

        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $izena = $row['izena'];
        $iraunaldia = $row['iraunaldia'];
        $generoa = $row['generoa'];
        $sailkapena = $row['sailkapena'];
        $sinopsia = $row['sinopsia'];
        $aktoreak = $row['aktoreak'];
        $zuzendaria = $row['zuzendaria'];
        $kartela = $row['kartela'];
        $trailerra = $row['trailerra'];
        echo "<title>$izena</title>";
    }
    ?>
</head>

<body>
    <!-- navbar-->
    <?PHP require_once("nav.php"); ?>
    <!-- Page Content-->
    <div class="container-pelikula px-4 px-lg-5">
        <!-- Heading Row-->
        <div class="row gx-4 gx-lg-5 my-5">
            <div class="card col-lg-5">
                <h1 class="font-weight-light"><?PHP echo $izena; ?></h1>
                <div class="col-lg-7 kartel-trailer">
                    <img class="img-fluid rounded mb-4 mb-lg-0" src="<?PHP echo $kartela; ?>" alt="..."
                        style="width: 350px; height: 500px;" />
                    <iframe width="560" height="315" src="<?php echo $trailerra; ?>" title="YouTube video player"
                        frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                        allowfullscreen>
                    </iframe>
                </div>
                <p class="text-white m-0"><b>Iraunaldia:</b> <?PHP echo $iraunaldia; ?> min</p>
                <p class="text-white m-0"><b>Generoa:</b> <?PHP echo $generoa; ?></p>
                <p class="text-white m-0"><b>Sailkapena:</b> <?PHP echo $sailkapena; ?></p>
                <p class="text-white m-0"><b>Sinopsia:</b> <?PHP echo $sinopsia; ?></p>
                <p class="text-white m-0"><b>Aktoreak:</b> <?PHP echo $aktoreak; ?></p>
                <p class="text-white m-0"><b>Zuzendaria:</b> <?PHP echo $zuzendaria; ?></p>
            </div>
        </div>
        <!-- Call to Action-->
        <div class="card text-white bg-secondary my-5 py-4 text-center">
            <div class="card-body">
                <?php
                //logueatuta dagoen erabiltzailea erreserbara eta logueatu gabekoa saioa hasieratzeko botoiak
                ?>
            </div>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>