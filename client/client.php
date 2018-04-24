<?php

require 'vendor/autoload.php';

use GuzzleHttp\Client;
use Psr\Http\Message\ResponseInterface;
use GuzzleHttp\Exception\RequestException;

$client = new Client([
	'base_uri' => 'http://newproject-161616.appspot.com/services/',
	'proxy' => [
		'http' => 'fr-proxy.groupinfra.com:3128'
	],
	'headers' => [
		'Accept' => 'application/json',
		'Content-Type' => 'application/json'
	]
]);

function getAccounts($client){
	$promise = $client->getAsync('accounts');
	$promise->then(
		function(ResponseInterface $res){
			echo $res->getBody();
		},
		function(RequestException $e){
			echo $e->getMessage()."\n";
		}
	);
}

function getApprovals($client){
	$promise = $client->getAsync('approvals');
	$promise->then(
		function(ResponseInterface $res){
			echo $res->getBody();
		},
		function(RequestException $e){
			echo $e->getMessage()."\n";
		}
	);
}

getAccounts($client);
getApprovals($client);

?>