<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?php require_once("head.php"); ?>
    <?php
    require_once("db.php");
    $conn = konexioaSortu();

    if (isset($_GET['idPelikula'])) {
        $idPelikula = intval($_GET['idPelikula']);
        $sql = "SELECT izena FROM pelikula WHERE id = $idPelikula";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $izena = $row['izena'];
    }
    if (isset($_GET['idOrdutegia'])) {
        $idOrdutegia = intval($_GET['idOrdutegia']);
        $sql = "SELECT ordua FROM ordutegia WHERE id = $idOrdutegia";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $ordua = $row['ordua'];
    }
    echo "<title>Erreserbatu</title>";
    ?>

    <script>
        let aukeratutakoEserlekuak = [];

        function getSeatPrice(eserlekuZbk) {
            if (eserlekuZbk >= 1 && eserlekuZbk <= 6) {
                return 12;
            } else if (eserlekuZbk >= 7 && eserlekuZbk <= 14) {
                return 10;
            } else if (eserlekuZbk >= 15 && eserlekuZbk <= 22) {
                return 8;
            } else if (eserlekuZbk >= 23 && eserlekuZbk <= 30) {
                return 6;
            }
            return 0;
        }

        function toggleSeat(eserlekuId, eserlekuZbk) {
            let seat = document.getElementById(`seat_${eserlekuId}`);
            let index = aukeratutakoEserlekuak.findIndex(item => item.id === eserlekuId);
            if (index > -1) {
                aukeratutakoEserlekuak.splice(index, 1);
                seat.style.fill = "green";
            } else {
                let price = getSeatPrice(eserlekuZbk);
                aukeratutakoEserlekuak.push({ id: eserlekuId, price: price });
                seat.style.fill = "orange";
            }
            let selectedCountText = document.querySelector('[aria-label="Language selected: English"]') ? "Number of Seats Selected:" : "Aukeratutako Eserleku Kopurua:";
            document.getElementById("selectedCount").innerText = `${selectedCountText} ${aukeratutakoEserlekuak.length}`;
        }

        function reserveSeats() {
            if (aukeratutakoEserlekuak.length === 0) return;
            let form = document.getElementById("erreserbaForm");
            form.seats.value = JSON.stringify(aukeratutakoEserlekuak);
            form.submit();
        }
    </script>

</head>

<body>
    <?php require_once("nav.php"); ?>

    <div class="card zinetitle col-lg-5" style="margin: 20px auto; width: max-content; padding: 10px;">
        <h1 class="text-center"><?php echo "$izena - $ordua - {$_GET['eguna']}"; ?></h1>
    </div>

    <?php
    if (isset($idPelikula) && isset($idOrdutegia)) {
        $sql = "SELECT 
                    eserlekua.id, 
                    eserlekua.zenbakia, 
                    eserlekua_ordutegian.beteta
                FROM eserlekua
                LEFT JOIN eserlekua_ordutegian 
                    ON eserlekua.id = eserlekua_ordutegian.id_eserleku 
                    AND eserlekua_ordutegian.id_ordutegi = $idOrdutegia
                WHERE eserlekua.id_Areto = (SELECT id_Areto FROM ordutegia WHERE id = $idOrdutegia)
                ORDER BY eserlekua.zenbakia DESC";

        $result = $conn->query($sql);
        echo '<div class="zinema-container">';
        echo '<div class="zinema px-4 px-lg-5" style="display: flex; flex-wrap: wrap; gap: 5px;">';
        while ($row = $result->fetch_assoc()) {
            $idEserleku = $row['id'];
            $zenbakia = $row['zenbakia'];
            $beteta = $row['beteta'];
            $color = $beteta ? 'red' : 'green';
            if ($zenbakia >= 1 && $zenbakia <= 6) {
                $fila = 1;
            } elseif ($zenbakia >= 7 && $zenbakia <= 14) {
                $fila = 2;
            } elseif ($zenbakia >= 15 && $zenbakia <= 22) {
                $fila = 3;
            } else {
                $fila = 4;
            }
            $onclick = !$beteta ? "onclick='toggleSeat($idEserleku, $zenbakia)'" : "";
            echo "<div class='seat' style='cursor: " . (!$beteta ? "pointer" : "default") . ";'>
                    <svg width='50' height='50' $onclick>
                        <rect id='seat_$idEserleku' width='50' height='50' style='fill:$color;' />
                        <text x='25' y='25' font-size='20' text-anchor='middle' fill='white' dy='.3em'>$zenbakia</text>
                    </svg>
                  </div>";
        }
        echo '<div class="pantaila" style="width: 100%; text-align: center; margin-top: 20px;"><h1>Pantaila</h1></div>';
        echo "</div>";
        echo "</div>";
    }
    ?>

    <div style="text-align: center; margin: 20px;">
        <p class="card col-lg-5" id="selectedCount" style="width: fit-content; margin-left: auto; margin-right: auto;">Aukeratutako Eserleku Kopurua: 0</p>
        <button onclick="reserveSeats()" class="btn">Erreserbatu</button>
    </div>

    <form id="erreserbaForm" method="POST" action="erreserbaEgin.php" style="display: none;">
        <input type="hidden" name="idPelikula" value="<?php echo $idPelikula; ?>">
        <input type="hidden" name="idOrdutegia" value="<?php echo $idOrdutegia; ?>">
        \n
        <input type="hidden" name="seats" value=\"\">\n
    </form>

    <?php require_once("footer+JS.php"); ?>
</body>

</html>