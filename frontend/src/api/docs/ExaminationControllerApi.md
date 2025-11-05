# ExaminationControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createExamination**](ExaminationControllerApi.md#createexamination) | **POST** /api/examinations |  |
| [**getExaminationDetail**](ExaminationControllerApi.md#getexaminationdetail) | **GET** /api/examinations/{id} |  |
| [**getExaminations**](ExaminationControllerApi.md#getexaminations) | **POST** /api/examinations/filter |  |
| [**updateExamination**](ExaminationControllerApi.md#updateexamination) | **PUT** /api/examinations/{id} |  |



## createExamination

> CustomApiResponseExaminationResponse createExamination(examinationRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { CreateExaminationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // ExaminationRequest
    examinationRequest: ...,
  } satisfies CreateExaminationRequest;

  try {
    const data = await api.createExamination(body);
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
| **examinationRequest** | [ExaminationRequest](ExaminationRequest.md) |  | |

### Return type

[**CustomApiResponseExaminationResponse**](CustomApiResponseExaminationResponse.md)

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


## getExaminationDetail

> CustomApiResponseExaminationResponse getExaminationDetail(id)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { GetExaminationDetailRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetExaminationDetailRequest;

  try {
    const data = await api.getExaminationDetail(body);
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

[**CustomApiResponseExaminationResponse**](CustomApiResponseExaminationResponse.md)

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


## getExaminations

> CustomApiResponsePageExaminationResponse getExaminations(searchFilter)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { GetExaminationsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies GetExaminationsRequest;

  try {
    const data = await api.getExaminations(body);
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

[**CustomApiResponsePageExaminationResponse**](CustomApiResponsePageExaminationResponse.md)

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


## updateExamination

> CustomApiResponseExaminationResponse updateExamination(id, examinationRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { UpdateExaminationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: id_example,
    // ExaminationRequest
    examinationRequest: ...,
  } satisfies UpdateExaminationRequest;

  try {
    const data = await api.updateExamination(body);
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
| **examinationRequest** | [ExaminationRequest](ExaminationRequest.md) |  | |

### Return type

[**CustomApiResponseExaminationResponse**](CustomApiResponseExaminationResponse.md)

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

