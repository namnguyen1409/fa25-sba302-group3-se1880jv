# ServiceCatalogControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create7**](#create7) | **POST** /api/examinations/service-catalog | |
|[**delete7**](#delete7) | **DELETE** /api/examinations/service-catalog/{id} | |
|[**getListServiceCatalogs**](#getlistservicecatalogs) | **POST** /api/examinations/service-catalog/filter | |
|[**update7**](#update7) | **PUT** /api/examinations/service-catalog/{id} | |

# **create7**
> CustomApiResponseServiceCatalogResponse create7(serviceCatalogRequest)


### Example

```typescript
import {
    ServiceCatalogControllerApi,
    Configuration,
    ServiceCatalogRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceCatalogControllerApi(configuration);

let serviceCatalogRequest: ServiceCatalogRequest; //

const { status, data } = await apiInstance.create7(
    serviceCatalogRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **serviceCatalogRequest** | **ServiceCatalogRequest**|  | |


### Return type

**CustomApiResponseServiceCatalogResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delete7**
> CustomApiResponseVoid delete7()


### Example

```typescript
import {
    ServiceCatalogControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceCatalogControllerApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.delete7(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getListServiceCatalogs**
> CustomApiResponsePageServiceCatalogResponse getListServiceCatalogs(searchFilter)


### Example

```typescript
import {
    ServiceCatalogControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceCatalogControllerApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.getListServiceCatalogs(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageServiceCatalogResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **update7**
> CustomApiResponseServiceCatalogResponse update7(serviceCatalogRequest)


### Example

```typescript
import {
    ServiceCatalogControllerApi,
    Configuration,
    ServiceCatalogRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceCatalogControllerApi(configuration);

let id: string; // (default to undefined)
let serviceCatalogRequest: ServiceCatalogRequest; //

const { status, data } = await apiInstance.update7(
    id,
    serviceCatalogRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **serviceCatalogRequest** | **ServiceCatalogRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseServiceCatalogResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

