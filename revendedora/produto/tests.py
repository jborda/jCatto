"""
This file demonstrates writing tests using the unittest module. These will pass
when you run "manage.py test".

Replace this with more appropriate tests for your application.
"""

from django.test import TestCase
from produto.models import Produto

class ProdutoTeste(TestCase):

    def test_add(self):
        self.assertEquals(Produto.objects.count(), 0)
	Produto.objects.get_or_create(
	    codigo = 'A2530F', nome = 'Perfume Boi Zebu'
	    )
        self.assertEqual(Produto.objects.count(), 1)
