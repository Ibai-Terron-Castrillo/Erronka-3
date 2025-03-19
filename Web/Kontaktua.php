<?php
session_start();
?>
<?php require_once("head.php"); ?>
<title>Kontaktuak</title>
</head>
<body>

    <?php require_once("nav.php"); ?>

    <div class="container mt-5">
        <div class="card bg-light p-4">
            <h2 style="color: gold;">Kontaktua</h2>
            <ul>
                <li><strong style="color: gold;">Helbidea:</strong> Kale Nagusia, 45, Donostia</li>
                <li><strong style="color: gold;">Telefonoa:</strong> +34 943 123 456</li>
                <li><strong style="color: gold;">Emaila:</strong> <a href="mailto:zurezinema@elumbral.eus" style="color: gold;">zurezinema@elumbral.eus</a></li>
                <li><strong style="color: gold;">Ordutegia:</strong> Astelehenetik Igandera, 10:00 - 00:00</li>
            </ul>
            <p>Gurekin harremanetan jarri nahi baduzu, erabili formularioa edo deitu/mezua bidali gure helbidera.</p>
        </div>
    </div>

    <div class="container mt-5">
        <div class="card bg-light p-4">
            <h1 style="color: gold;">Jarri gurekin harremanetan</h1>
            <p>Behar duzun edozein informaziorako, mesedez, bidali zure mezua.</p>

            <form id="contactForm" class="mt-4">
                <div class="mb-3">
                    <label for="email" class="form-label" style="color: gold;">Zure emaila</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="mezua" class="form-label" style="color: gold;">Zure mezua</label>
                    <textarea class="form-control" id="mezua" name="mezua" rows="4" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Bidali</button>
            </form>
        </div>
    </div>

    <?php require_once("footer+JS.php"); ?>

   

</body>
</html>
