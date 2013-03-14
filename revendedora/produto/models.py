from django.db import models

class Produto(models.Model):
    codigo = models.CharField(max_length=50)
    nome = models.TextField()
