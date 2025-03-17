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
$limit = 6; 
$offset = ($page - 1) * $limit;

try {
    $stmt = $pdo->prepare("SELECT id, izena, kartela FROM pelikula LIMIT :limit OFFSET :offset");
    $stmt->bindValue(':limit', $limit, PDO::PARAM_INT);
    $stmt->bindValue(':offset', $offset, PDO::PARAM_INT);
    $stmt->execute();

    $movies = $stmt->fetchAll(PDO::FETCH_ASSOC);

    header('Content-Type: application/json');
    echo json_encode($movies);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Errorea" . $e->getMessage()]);
    exit;
}
?>
