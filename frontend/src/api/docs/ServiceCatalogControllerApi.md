# ServiceCatalogControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create7**](ServiceCatalogControllerApi.md#create7) | **POST** /api/examinations/service-catalog |  |
| [**delete7**](ServiceCatalogControllerApi.md#delete7) | **DELETE** /api/examinations/service-catalog/{id} |  |
| [**getListServiceCatalogs**](ServiceCatalogControllerApi.md#getlistservicecatalogs) | **POST** /api/examinations/service-catalog/filter |  |
| [**update7**](ServiceCatalogControllerApi.md#update7) | **PUT** /api/examinations/service-catalog/{id} |  |



## create7

> CustomApiResponseServiceCatalogResponse create7(serviceCatalogRequest)



### Example

```ts
import {
  Configuration,
  ServiceCatalogControllerApi,
} from '';
import type { Create7Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceCatalogControllerApi(config);

  const body = {
    // ServiceCatalogRequest
    serviceCatalogRequest: ...,
  } satisfies Create7Request;

  try {
    const data = await api.create7(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **serviceCatalogRequest** | [ServiceCatalogRequest](ServiceCatalogRequest.md) |  | |

### Return type

[**CustomApiResponseServiceCatalogResponse**](CustomApiResponseServiceCatalogResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## delete7

> CustomApiResponseVoid delete7(id)



### Example

```ts
import {
  Configuration,
  ServiceCatalogControllerApi,
} from '';
import type { Delete7Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceCatalogControllerApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies Delete7Request;

  try {
    const data = await api.delete7(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getListServiceCatalogs

> CustomApiResponsePageServiceCatalogResponse getListServiceCatalogs(searchFilter)



### Example

```ts
import {
  Configuration,
  ServiceCatalogControllerApi,
} from '';
import type { GetListServiceCatalogsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceCatalogControllerApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies GetListServiceCatalogsRequest;

  try {
    const data = await api.getListServiceCatalogs(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageServiceCatalogResponse**](CustomApiResponsePageServiceCatalogResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## update7

> CustomApiResponseServiceCatalogResponse update7(id, serviceCatalogRequest)



### Example

```ts
import {
  Configuration,
  ServiceCatalogControllerApi,
} from '';
import type { Update7Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceCatalogControllerApi(config);

  const body = {
    // string
    id: id_example,
    // ServiceCatalogRequest
    serviceCatalogRequest: ...,
  } satisfies Update7Request;

  try {
    const data = await api.update7(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` |  | [Defaults to `undefined`] |
| **serviceCatalogRequest** | [ServiceCatalogRequest](ServiceCatalogRequest.md) |  | |

### Return type

[**CustomApiResponseServiceCatalogResponse**](CustomApiResponseServiceCatalogResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

