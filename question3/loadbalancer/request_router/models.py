from django.db import models


class Server(models.Model):
    server_ip = models.CharField(max_length=12)
    state = models.CharField(max_length=200)
