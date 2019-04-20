<?php

if(isset($_POST['submit'])) { 
     $input = $_POST['input'];
     $output = $_POST['output'];
     if(isset($_POST['if'])) {
        $if = 'true';
     }else{
        $if = 'false';
     }
     if(isset($_POST['if_else'])) {
        $ifelse = 'true';
     }else{
         $ifelse = 'false';
     }
     if(isset($_POST['else_if'])) {
        $elseif = 'true';
     }else{
        $elseif = 'false';
     }
     if(isset($_POST['switch'])) {
        $switch = 'true';
     }else{
        $switch = 'false';
     }
     if(isset($_POST['for_loop'])) {
        $forloop = 'true';
     }else{
        $forloop = 'false';
     }
     if(isset($_POST['while'])) {
        $while = 'true';
     }else{
        $while = 'false';
     }
     $compile = $_POST['comp'];
     $Document = $_POST['dom'];
     $code = $_POST['code'];
     $statment = $_POST['stat'];
     $grade = $_POST['grade'];
     $jfile= $_POST['jfile'];
    
     $result = shell_exec('javac marker.java');
    
    $result = shell_exec("java marker $jfile $input $output $if $ifelse $elseif $switch $forloop $while $grade $compile $Document $code $statment");
        
    
    $result = str_replace("O-","<br>O-",$result);
    $result = str_replace("X-","<br>X-",$result);
    echo $result;
}
?>
