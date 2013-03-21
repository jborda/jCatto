from django.shortcuts import render_to_response
from models import Produto

def lista(request):
	lista = Produto.objects.all()
	return render_to_response('produto/lista.html')
