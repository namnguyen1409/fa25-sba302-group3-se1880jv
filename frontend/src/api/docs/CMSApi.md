# CMSApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**archive**](#archive) | **POST** /api/cms/contents/{id}/archive | |
|[**create9**](#create9) | **POST** /api/cms/contents | |
|[**delete9**](#delete9) | **DELETE** /api/cms/contents/{id} | |
|[**filter10**](#filter10) | **POST** /api/cms/contents/filter | |
|[**getById5**](#getbyid5) | **GET** /api/cms/contents/{id} | |
|[**getBySlug**](#getbyslug) | **GET** /api/cms/contents/slug/{slug} | |
|[**publish**](#publish) | **POST** /api/cms/contents/{id}/publish | |
|[**update9**](#update9) | **PUT** /api/cms/contents/{id} | |

# **archive**
> CustomApiResponseContentResponse archive()


### Example

```typescript
import {
    CMSApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.archive(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseContentResponse**

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

# **create9**
> CustomApiResponseContentResponse create9(contentRequest)


### Example

```typescript
import {
    CMSApi,
    Configuration,
    ContentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let contentRequest: ContentRequest; //

const { status, data } = await apiInstance.create9(
    contentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **contentRequest** | **ContentRequest**|  | |


### Return type

**CustomApiResponseContentResponse**

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

# **delete9**
> CustomApiResponseVoid delete9()


### Example

```typescript
import {
    CMSApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.delete9(
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

# **filter10**
> CustomApiResponsePageContentResponse filter10(searchFilter)


### Example

```typescript
import {
    CMSApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter10(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageContentResponse**

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

# **getById5**
> CustomApiResponseContentResponse getById5()


### Example

```typescript
import {
    CMSApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getById5(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseContentResponse**

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

# **getBySlug**
> CustomApiResponseContentResponse getBySlug()


### Example

```typescript
import {
    CMSApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let slug: string; // (default to undefined)

const { status, data } = await apiInstance.getBySlug(
    slug
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **slug** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseContentResponse**

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

# **publish**
> CustomApiResponseContentResponse publish()


### Example

```typescript
import {
    CMSApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.publish(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseContentResponse**

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

# **update9**
> CustomApiResponseContentResponse update9(contentRequest)


### Example

```typescript
import {
    CMSApi,
    Configuration,
    ContentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new CMSApi(configuration);

let id: string; // (default to undefined)
let contentRequest: ContentRequest; //

const { status, data } = await apiInstance.update9(
    id,
    contentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **contentRequest** | **ContentRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseContentResponse**

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

