from rest_framework.response import Response

# for function based views
from rest_framework.decorators import api_view


# for apiroot reverse
from rest_framework.reverse import reverse


@api_view()
def api_root(request):
    """
    The root of all APIs, serves as a basic presentation of the APIs aviliable,
    however needs manual additions of the functions.
    reverse() serves as a url call to each function views.
    """
    return Response({
        'balance': reverse('balance', request=request),
    })


@api_view()
def balance(request):
    return Response({"message": "url"})
