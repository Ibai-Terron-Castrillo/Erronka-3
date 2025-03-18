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
    <!-- navbar-->
    <?PHP require_once("nav.php"); ?>
    <!-- Page Content-->
    <div class="login">
        <!-- Heading Row-->
        <div class="row gx-4 gx-lg-5 align-items-center my-5">
            <div class="card col-lg-5">
                <h1 class="font-weight-light">Kontua Sortu</h1>
                <form action="insert.php" method="post">
                    <div class="mb-3">
                        <label for="izena" class="form-label">Izena</label>
                        <input type="text" class="form-control" id="izena" aria-describedby="izena" name="izena"
                            required autofocus>
                    </div>
                    <div class="mb-3">
                        <label for="abizena1" class="form-label">1. Abizena</label>
                        <input type="text" class="form-control" id="abizena1" aria-describedby="abizena1"
                            name="abizena1" required>
                    </div>
                    <div class="mb-3">
                        <label for="abizena2" class="form-label">2. Abizena</label>
                        <input type="text" class="form-control" id="abizena2" aria-describedby="abizena2"
                            name="abizena2">
                    </div>
                    <div class="mb-3">
                        <label for="nan" class="form-label">NAN</label>
                        <input type="text" class="form-control" id="nan" aria-describedby="nan" name="nan" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Posta Elektronikoa</label>
                        <input type="email" class="form-control" id="email" aria-describedby="email" name="email"
                            required>
                    </div>
                    <div class="mb-3">
                        <label for="pass" class="form-label">Pasahitza</label>
                        <input type="password" class="form-control" id="pass" name="pass" required>
                    </div>
                    <div class="mb-3">
                        <label for="passconfirm" class="form-label">Pasahitza Errepikatu</label>
                        <input type="password" class="form-control" id="passconfirm" name="passconfirm" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Erregistratu</button>
                </form>
            </div>
        </div>
    </div>
    <?PHP require_once("footer+JS.php"); ?>
</body>

</html>