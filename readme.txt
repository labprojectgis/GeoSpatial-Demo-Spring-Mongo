use test;

db.locations.insertMany([
	{
		"subject": "Parque del Retiro",
		"location": {
			"type": "Point",
			"coordinates": [-3.674908, 40.411335]
		}
	},
	{
		"subject": "Puerta del Sol",
		"location": {
			"type": "Point",
			"coordinates": [-3.703339, 40.416729]
		}
	},
	{
		"subject": "Palacio Real",
		"location": {
			"type": "Point",
			"coordinates": [-3.7083305, 40.41749833]
		}
	}
]);

https://8080-pink-roadrunner-0ziibk37.ws-eu03.gitpod.io/geodata/search/location?lat=-3.674908&long=40.411335

https://8080-pink-roadrunner-0ziibk37.ws-eu03.gitpod.io/geodata/search/nearby?lat=-3.674908&long=40.411335&d=10000

https://8080-pink-roadrunner-0ziibk37.ws-eu03.gitpod.io/geodata/search/withinCircle?x=-3.674908&y=40.411335&radius=1
