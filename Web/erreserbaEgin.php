<?php
session_start();
require_once("db.php");
$conn = konexioaSortu();

if (!isset($_POST['idPelikula'], $_POST['idOrdutegia'], $_POST['seats'])) {
    die("Parametroak falta dira.");
}

$idPelikula = intval($_POST['idPelikula']);
$idOrdutegia = intval($_POST['idOrdutegia']);
$seats_json = $_POST['seats'];

$idBezero = intval($_SESSION['id']);

$seats = json_decode($seats_json, true);
if ($seats === null) {
    die("Datos de asientos inválidos.");
}
$kopurua = count($seats);

try {
    $conn->begin_transaction();

    $stmt = $conn->prepare("INSERT INTO erreserba (id_bezero, id_ordutegi, kopurua, egoera) VALUES (?, ?, ?, 'Prozesatzen')");
    $stmt->bind_param("iii", $idBezero, $idOrdutegia, $kopurua);
    $stmt->execute();
    $erreserba_id = $conn->insert_id;
    $stmt->close();

    $stmt = $conn->prepare("INSERT INTO sarrera (id_erreserba, id_eserleku, prezioa) VALUES (?, ?, ?)");
    foreach ($seats as $seat) {
        $idEserleku = intval($seat['id']);
        $prezioa = floatval($seat['price']);
        $stmt->bind_param("iid", $erreserba_id, $idEserleku, $prezioa);
        $stmt->execute();

        $update_stmt = $conn->prepare("UPDATE eserlekua_ordutegian SET beteta = 1 WHERE id_eserleku = ? AND id_ordutegi = ?");
        $update_stmt->bind_param("ii", $idEserleku, $idOrdutegia);
        $update_stmt->execute();
        $update_stmt->close();
    }
    $stmt->close();

    $conn->commit();

    echo "<script>alert('Erreserba zuzen egin da.'); window.location.href='index.php';</script>";
    exit();
} catch (Exception $e) {
    $conn->rollback();
    die("Errorea: " . $e->getMessage());
}
?>
