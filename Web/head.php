<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="icon" type="image/x-icon" href="assets/logo.png" />
<?php
if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
    $xml = simplexml_load_file("conf.xml");
    $userId = $_SESSION['id'];
    $user = $xml->xpath("//user[id='$userId']")[0];
    $theme = (string)$user->theme;
    echo '<link href="css/styles-' . $theme . '.css" rel="stylesheet" />';
} else {
    echo '<link href="css/styles-night.css" rel="stylesheet" />';
}
?>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>