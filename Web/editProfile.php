<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <title>Profila Editatu</title>
</head>

<body>
    <?PHP require_once("nav.php");
    require_once("db.php");
    $conn = konexioaSortu();
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
    <div class="login">
        <div class="row gx-4 gx-lg-5 align-items-center my-5">
            <div class="card col-lg-5">
                <h1 class="font-weight-light">Profila Editatu</h1>
                <form action="update.php" method="post">
                    <div class="mb-3">
                        <label for="izena" class="form-label">Izena</label>
                        <input type="text" class="form-control" id="izena" aria-describedby="izena" name="izena"
                            value="<?PHP echo $izena; ?>" required autofocus>
                    </div>
                    <div class="mb-3">
                        <label for="abizena1" class="form-label">1. Abizena</label>
                        <input type="text" class="form-control" id="abizena1" aria-describedby="abizena1"
                            name="abizena1" value="<?PHP echo $abizena1; ?>" required>
                    </div>
                    <div class="mb-3">
                        <label for="abizena2" class="form-label">2. Abizena</label>
                        <input type="text" class="form-control" id="abizena2" aria-describedby="abizena2"
                            name="abizena2" value="<?PHP echo $abizena2; ?>">
                    </div>
                    <div class="mb-3">
                        <label for="nan" class="form-label">NAN</label>
                        <input type="text" class="form-control" id="nan" aria-describedby="nan" name="nan"
                            value="<?PHP echo $nan; ?>" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Posta Elektronikoa</label>
                        <input type="email" class="form-control" id="email" aria-describedby="email" name="email"
                            value="<?PHP echo $email; ?>" required>
                    </div>
                    <div class="mb-3">
                        <label for="pass" class="form-label">Pasahitza</label>
                        <input type="password" class="form-control" id="pass" name="pass"
                            value="<?PHP echo $pasahitza; ?>" required>
                    </div>
                    <div class="mb-3">
                        <label for="passconfirm" class="form-label">Pasahitza Errepikatu</label>
                        <input type="password" class="form-control" id="passconfirm" name="passconfirm"
                            value="<?PHP echo $pasahitza; ?>" required>
                    </div>
                    <div class="mb-3">
                        <label for="theme" class="form-label">Itxura</label>
                        <select name="theme" id="theme" class="form-control">
                            <?php
                                $xml = simplexml_load_file("conf.xml");
                                $userTheme = (string)$xml->xpath("//user[id='$id']/theme")[0];
                            ?>
                            <option value="night" <?php if ($userTheme == 'night') echo 'selected'; ?>>Night</option>
                            <option value="day" <?php if ($userTheme == 'day') echo 'selected'; ?>>Day</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Aldatu</button>
                </form>
            </div>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>