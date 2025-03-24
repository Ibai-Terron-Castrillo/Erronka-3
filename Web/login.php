<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <title>Saioa Hasi</title>
</head>

<body>
    <?PHP require_once("nav.php"); ?>
    <div class="login">
        <div class="row gx-4 gx-lg-5 align-items-center my-5">
            <div class="card col-lg-5">
                <h1 class="font-weight-light">Saioa Hasi</h1>
                <form action="autentikatu.php" method="post">
                    <div class="mb-3">
                        <label for="email" class="form-label">Posta Elektronikoa</label>
                        <input type="email" class="form-control" id="email" aria-describedby="email"
                            name="email" required autofocus>
                    </div>
                    <div class="mb-3">
                        <label for="pass" class="form-label">Pasahitza</label>
                        <input type="password" class="form-control" id="pass" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Saioa Hasi</button>
                </form>
                <p>Ez duzu konturik? <a href="erregistratu.php">ERREGISTRATU HEMEN!</a></p>
            </div>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>