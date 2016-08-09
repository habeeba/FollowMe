<?php
  if(isset($_POST['submit'])){
  if(preg_match("/^[  a-zA-Z]+/", $_POST['name'])){
  $name=$_POST['name'];
  $sql="SELECT  formalemail, name FROM staff WHERE name LIKE '%" . $name .  "%";
  $result=mysql_query($sql);
  while($row=mysql_fetch_array($result)){
          $name  =$row['name'];
          $ID=$row['formalemail'];
  echo "<ul>\n";
  echo "<li>" . "<a  href=\"search.php?id=$ID\">.$name.</a>"."</li>\n";
  echo "</ul>";
  }
  }
  else{
  echo  "<p>Please enter a search query</p>";
  }
  }
?>