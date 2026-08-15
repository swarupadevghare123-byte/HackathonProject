<?php
include 'db_config.php';

$sql = "SELECT * FROM doctors";
$result = $conn->query($sql);

$doctors = [];
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $doctors[] = $row;
    }
    echo json_encode(["status" => "success", "doctors" => $doctors]);
} else {
    echo json_encode(["status" => "success", "doctors" => []]);
}

$conn->close();
?>
