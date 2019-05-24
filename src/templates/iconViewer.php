<?php
#HEADER
$home = 'office';
$pwd  = `pwd`;
$type = 'png';
$sp   = 5; 

$office = explode($home, $pwd);
$office_path = array_pop($office);

$links = explode('/', $office_path);
$current = array_pop($links);

foreach ($links as $val)
    {
    $link_path .= "$val/";
    print "<a href ='/$home$link_path'>$val</a>";
    print " / "; 
    }

print "$current";
print " / "; 
print "<br/>";

$cmd = "ls | grep '.$type';";
exec($cmd, $out);

$hw =array_pop($links);

foreach ($out as $val)
    {
    print "<img src =\"$val\" hspace=$sp vspace=$sp height=$hw width=$hw alt=''>";
    }
?>
