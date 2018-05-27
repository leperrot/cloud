<?php

require 'vendor/autoload.php';

use GuzzleHttp\Client;
use Psr\Http\Message\ResponseInterface;
use GuzzleHttp\Exception\RequestException;

$client_gae = new Client([
	'base_uri' => 'http://inf63app3.appspot.com/services/',
	'headers' => [
		'Accept' => 'application/json',
		'Content-Type' => 'application/json'
	]
]);

$client_heroku = new Client([
	'base_uri' => 'http://loan-approval.herokuapp.com/loan',
	'headers' => [
		'Accept' => 'application/json',
		'Content-Type' => 'application/json'
	]
]);

function addAccount($client, $nom, $prenom, $risk, $amount){
	$client->post('accounts/new', [
		GuzzleHttp\RequestOptions::JSON => [
			'nom' => $nom,
			'prenom' => $prenom,
			'risk' => $risk,
			'amount' => $amount
		]
	]);
}

function getAccounts($client){
	return $client->get('accounts');
}

function loan($client, $nom, $account, $amount){
	return $client->post('', [
		GuzzleHttp\RequestOptions::JSON => [
			'nom' => $nom,
			'account' => $account,
			'amount' => $amount
		]
	]);
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

echo "<p>Deleting all accounts</p>\n";
$client_gae->delete('accounts/deleteAll');

echo "<p>Deleting all approvals</p>\n";
$client_gae->delete('approvals/deleteAll');

echo "<p>Adding two accounts</p>\n";
addAccount($client_gae, 'Philippe', 'Jean', 'low', 200);
addAccount($client_gae, 'Sa', 'Samo', 'low', 500);

echo "<p>Retrieving accounts</p>\n";
$res = getAccounts($client_gae);
if($res->getstatusCode() == 200){
	echo $res->getBody()."\n";
	$json = json_decode($res->getBody());
	$account1 = $json[0];
	$account2 = $json[1];
} else {
	echo $res->getMessage()."\n";
}

echo "<p>Jean Philippe ask for a 5000$ loan</p>\n";
$res = loan($client_heroku, 'Voiture', $account1->account, 5000);
if($res->getstatusCode() == 200){
	echo $res->getBody()."\n";
	$json = json_decode($res->getBody());
} else {
	echo $res->getMessage()."\n";
}

echo "<p>Jean Philippe ask for another 5000$ loan</p>\n";
$res = loan($client_heroku, 'Une autre Voiture', $account1->account, 5000);
if($res->getstatusCode() == 200){
	echo $res->getBody()."\n";
} else {
	echo $res->getMessage()."\n";
}

echo "<p>Jean Philippe wonder why his second loan was refused and checked his account</p>\n";
$res = $client_gae->get('check/'.$account1->account);
if($res->getstatusCode() == 200){
	echo $res->getBody()."\n";
} else {
	echo $res->getMessage()."\n";
}

echo "<p>While Jean Philippe was crying, Samo Sa asked for a 200$ loan</p>\n";
$res = loan($client_heroku, 'Stuff', $account2->account, 200);
if($res->getstatusCode() == 200){
	echo $res->getBody()."\n";
} else {
	echo $res->getMessage()."\n";
}

echo "<p>Retrieving all approvals</p>\n";
getApprovals($client_gae);

?>