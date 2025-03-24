<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <?PHP require_once("db.php");
    $conn = konexioaSortu();
    echo '<title>' . $_SESSION["izena"] . ' - Profila</title>';
    ?>
</head>

<body>
    <?PHP require_once("nav.php");
    if (isset($_SESSION['id'])) {
        $id = intval($_SESSION['id']);
        $sql = "SELECT 
    `bezeroa`.`izena`,
    `bezeroa`.`abizena1`,
    `bezeroa`.`abizena2`,
    `bezeroa`.`nan`,
    `bezeroa`.`email`,
    `bezeroa`.`pasahitza`
FROM `erronka3`.`bezeroa` WHERE id = $id";

        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $izena = $row['izena'];
        $abizena1 = $row['abizena1'];
        $abizena2 = $row['abizena2'];
        $nan = $row['nan'];
        $email = $row['email'];
        $pasahitza = $row['pasahitza'];
    }
    ?>
    <div class="container profile px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 my-5">
            <div class="card col-lg-5">
                <h1 class="font-weight-light"><?PHP echo $izena; ?></h1>
                <p class="text-white m-0"><b>1. Abizena:</b> <?PHP echo $abizena1; ?></p>
                <p class="text-white m-0"><b>2. Abizena:</b> <?PHP echo $abizena2; ?></p>
                <p class="text-white m-0"><b>NAN:</b> <?PHP echo $nan; ?></p>
                <p class="text-white m-0"><b>Posta Elektronikoa:</b> <?PHP echo $email; ?></p>
                <p class="text-white m-0"><b>Pasahitza:</b> <?PHP echo $pasahitza; ?></p>
                <?PHP
                $xml = simplexml_load_file("conf.xml");
                $userId = $_SESSION['id'];
                $user = $xml->xpath("//user[id='$userId']")[0];
                $theme = (string)$user->theme;
                echo "<p class='text-white m-0'><b>Itxura:</b> $theme</p>";
                ?>
                <div>
                    <a href="editProfile.php" class="btn btn-primary">Editatu Profila</a>
                    <a href="deleteProfile.php" class="btn btn-danger">Ezabatu Profila</a>
                    <a href="logout.php" class="btn btn-secondary">Saioa Itxi</a>
                </div>
            </div>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>