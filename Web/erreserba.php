<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <?PHP require_once("db.php");
    $conn = konexioaSortu();

    if (isset($_GET['idPelikula'])) {
        $idPelikula = intval($_GET['idPelikula']);
        $sql = "SELECT 
        `pelikula`.`izena`
FROM `erronka3`.`pelikula` WHERE id = $idPelikula";

        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $izena = $row['izena'];
    }
    if (isset($_GET['idOrdutegia'])) {
        $idOrdutegia = intval($_GET['idOrdutegia']);
        $sql = "SELECT 
        `ordutegia`.`ordua`
FROM `erronka3`.`ordutegia` WHERE id = $idOrdutegia";

        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $ordua = $row['ordua'];
    }
    echo "<title>$izena - Erreserbatu - $ordua</title>";
    ?>
</head>

<body>
    <!-- navbar-->
    <?PHP require_once("nav.php"); ?>

    <?php
    if (isset($idPelikula) && isset($idOrdutegia)) {
        $sql = "SELECT 
                `eserlekua`.`id`, `eserlekua`.`id_Areto`, `eserlekua`.`zenbakia`, `eserlekua_ordutegian`.`beteta`
                FROM `eserlekua`
                LEFT JOIN `eserlekua_ordutegian` ON `eserlekua`.`id` = `eserlekua_ordutegian`.`id_eserleku` AND `eserlekua_ordutegian`.`id_ordutegi` = $idOrdutegia
                WHERE `eserlekua`.`id_Areto` = (SELECT `id_Areto` FROM `ordutegia` WHERE `id` = $idOrdutegia) order by `eserlekua`.`zenbakia` desc";

        $result = $conn->query($sql);
       
        echo '<div class="zinema px-4 px-lg-5">';
        while ($row = $result->fetch_assoc()) {
            $idEserleku = $row['id'];
            $idAreto = $row['id_Areto'];
            $zenbakia = $row['zenbakia'];
            $beteta = $row['beteta'];
            $color = $beteta ? 'red' : 'green';
            echo "<div class='seat'>
            <svg width='50' height='50'>
                <rect width='50' height='50' style='fill:$color;' />
                <text x='25' y='25' font-size='20' text-anchor='middle' fill='white' dy='.3em'>$zenbakia</text>
            </svg>
              </div>";
        }
        echo '<div class="pantaila">
        <h1>Pantaila</h1>
    </div>';
        echo "</div>";
    } ?>




    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>