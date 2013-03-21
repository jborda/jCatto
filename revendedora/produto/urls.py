from django.conf.urls import patterns, include, url

urlpatterns = patterns('produto',
    url(r'^$', 'views.lista'),
    # Examples:
    # url(r'^$', 'revendedora.views.home', name='home'),
    # url(r'^revendedora/', include('revendedora.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    # url(r'^admin/', include(admin.site.urls)),
)
