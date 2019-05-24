<?php
#HEADER
$home = 'office';
$pwd  = `pwd`;

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
print "<br/>";

$cmd = "ls -d */";
exec($cmd, $out);

$hw =array_pop($links);

print "<ul>";
foreach ($out as $val)
    {
    print "<li><a href =\"$val\">$val</a></li>";
    }
print "</ul>";
?>
