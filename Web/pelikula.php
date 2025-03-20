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
        echo "<title>$izena - El Umbral</title>";
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
                if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
                    $sql1 = "SELECT aretoa.izena AS aretoa, ordutegia.ordua AS ordua, ordutegia.id AS id_ordutegia, ordutegia.eguna AS eguna
FROM ordutegia
JOIN aretoa ON ordutegia.id_areto = aretoa.id
WHERE ordutegia.id_pelikula = ?
";
                    $stmt = $conn->prepare($sql1);
                    $stmt->bind_param("i", $id);
                    $stmt->execute();
                    $result = $stmt->get_result();
                    if ($result->num_rows > 0) {
                        echo "<h2 class='text-white m-0'>Ordutegiak:</h2>";
                        while ($row = $result->fetch_assoc()) {
                            $aretoa = $row['aretoa'];
                            $ordua = $row['ordua'];
                            $id_ordutegia = $row['id_ordutegia'];
                            $eguna = $row['eguna'];
                            echo "<a href='erreserba.php?idOrdutegia=$id_ordutegia&idPelikula=$id&eguna=$eguna' class='btn btn-primary' style='margin-right: 2%; margin-top: 2%;'>$aretoa - $eguna - $ordua</a>";
                        }
                    } else {
                        echo "<p class='text-white m-0'>Ez dago informaziorik</p>";
                    }

                } else {
                    echo '<a href="login.php" class="btn btn-primary">Hasi saioa erreserbak egiteko</a>';
                }
                ?>
            </div>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>