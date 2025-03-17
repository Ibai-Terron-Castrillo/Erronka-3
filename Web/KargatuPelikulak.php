<?php
$host = 'localhost';
$dbname = 'erronka3';
$user = 'root';
$password = '1MG2024';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $user, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Errorea" . $e->getMessage()]);
    exit;
}

$page = isset($_GET['page']) ? (int)$_GET['page'] : 1;

if (isset($_GET['search'])&& $_GET['search'] != "") {
    $search = $_GET['search'];
}
if (isset($_GET['generoa'])&& $_GET['generoa'] != "") {
    $generoa = $_GET['generoa'];
}
if (isset($_GET['sailkapena'])&& $_GET['sailkapena'] != "") {
    $sailkapena = $_GET['sailkapena'];
}

$limit = 6; 
$offset = ($page - 1) * $limit;

try {
    if (isset($generoa) && isset($sailkapena) && isset($search)) {
        $stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula WHERE generoa = :generoa AND sailkapena = :sailkapena AND izena LIKE :search LIMIT :limit OFFSET :offset");
        $search = "%$search%";
        $stmt->bindParam(':generoa', $generoa, PDO::PARAM_STR);
        $stmt->bindParam(':sailkapena', $sailkapena, PDO::PARAM_STR);
        $stmt->bindParam(':search', $search, PDO::PARAM_STR);
        $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
        $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
        $stmt->execute();
    } else if (isset($generoa) && isset($sailkapena)) {
        $stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula WHERE generoa = :generoa AND sailkapena = :sailkapena LIMIT :limit OFFSET :offset");
        $stmt->bindParam(':generoa', $generoa, PDO::PARAM_STR);
        $stmt->bindParam(':sailkapena', $sailkapena, PDO::PARAM_STR);
        $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
        $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
        $stmt->execute();
    } else if (isset($generoa)) {
        $stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula WHERE generoa = :generoa LIMIT :limit OFFSET :offset");
        $stmt->bindParam(':generoa', $generoa, PDO::PARAM_STR);
        $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
        $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
        $stmt->execute();
    } else if (isset($sailkapena)) {
        $stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula WHERE sailkapena = :sailkapena LIMIT :limit OFFSET :offset");
        $stmt->bindParam(':sailkapena', $sailkapena, PDO::PARAM_STR);
        $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
        $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
        $stmt->execute();
    } else if (isset($search)) {
        $stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula WHERE izena LIKE :search LIMIT :limit OFFSET :offset");
        $search = "%$search%";
        $stmt->bindParam(':search', $search, PDO::PARAM_STR);
        $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
        $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
        $stmt->execute();
    } else {
        $stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula LIMIT :limit OFFSET :offset");
        $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
        $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
        $stmt->execute();
    }
    /*$stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula LIMIT :limit OFFSET :offset");
    $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
    $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
    $stmt->execute();*/

    $movies = $stmt->fetchAll(PDO::FETCH_ASSOC);

    header('Content-Type: application/json');
    echo json_encode($movies);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Errorea" . $e->getMessage()]);
    exit;
}
?>
