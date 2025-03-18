<?php
session_start();
?>
<!DOCTYPE html>
<html lang="eu">

<head>
    <?PHP require_once("head.php"); ?>
    <title>Profila Editatu</title>
</head>
<script>

    function ezabatu() {
        var r = confirm("Ziur zaude zure kontua ezabatu nahi duzula?");
        if (r == true) {
            window.location.href = "deleteProfile.php?action=delete";
        }
    }
</script>

<?php
if (isset($_GET['action']) && $_GET['action'] == 'delete') {
    require_once "db.php";
    $conn = konexioaSortu();
    $id = intval($_SESSION['id']);
    $sql = "DELETE FROM `erronka3`.`bezeroa` WHERE id = $id";
    $result = $conn->query($sql);
    session_destroy();
    header('Location: index.php');
    exit();
}else{
    echo "<script>
    ezabatu();
  </script>";
}
?>