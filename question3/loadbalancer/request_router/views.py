from rest_framework.response import Response

# for function based views
from rest_framework.decorators import api_view

# for apiroot reverse
from rest_framework.reverse import reverse
from request_router.models import Server


@api_view()
def api_root(request):
    return Response({
        'balance': reverse('balance', request=request),
    })


@api_view()
def balance(request):
    hash_value = hash(request.GET.get("type") + request.get_host() + request.GET.get("uri"))
    server_id = hash_value % Server.objects.all().__len__()
    return Response(
        {"server_ip": Server.objects.all()[server_id].server_ip,
         "server_state": Server.objects.all()[server_id].state})
